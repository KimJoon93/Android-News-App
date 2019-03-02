package com.example.android.newsfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsData>> {

    public static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private static final String NEWS_REQUEST_URL = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private NewsAdapter mnewsAdapter;
    private static final int NEWS_LOADER_ID = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        mnewsAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsData>());
        recyclerView.setAdapter(mnewsAdapter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        return view;
    }

    @NonNull
    @Override
    public Loader<List<NewsData>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(getActivity(), NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsData>> loader, List<NewsData> newsData) {
        mnewsAdapter.clearAll();
        if (newsData != null && !newsData.isEmpty()) {
            mnewsAdapter.addAll(newsData);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsData>> loader) {
        mnewsAdapter.clearAll();
    }

}
