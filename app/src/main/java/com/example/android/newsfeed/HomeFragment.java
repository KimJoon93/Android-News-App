package com.example.android.newsfeed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsData>> {

    public static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private static final String NEWS_REQUEST_URL = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private NewsAdapter mnewsAdapter;
    private static final int NEWS_LOADER_ID = 1;

    private TextView mEmptyStateTextView;

    private View mLoadingIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        EmptyRecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        mLoadingIndicator = view.findViewById(R.id.loading_indicator);

        mEmptyStateTextView = view.findViewById(R.id.empty_view);
        mRecyclerView.setEmptyView(mEmptyStateTextView);

        mnewsAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsData>());
        mRecyclerView.setAdapter(mnewsAdapter);
        checkNetworkConnection();
        return view;
    }

    @NonNull
    @Override
    public Loader<List<NewsData>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(getActivity(), NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsData>> loader, List<NewsData> newsData) {

        mLoadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_news);
        mnewsAdapter.clearAll();

        if (newsData != null && !newsData.isEmpty()) {
            mnewsAdapter.addAll(newsData);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsData>> loader) {
        mnewsAdapter.clearAll();
    }

    private void checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isConnected = networkInfo != null &&
                networkInfo.isConnected();
        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.NoInternet);
        }
    }

}
