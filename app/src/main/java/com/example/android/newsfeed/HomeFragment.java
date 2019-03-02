package com.example.android.newsfeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private static final String NEWS_REQUEST_URL = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private  NewsAdapter mnewsAdapter;

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
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(NEWS_REQUEST_URL);

        return view;
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, List<NewsData>> {

        @Override
        protected List<NewsData> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<NewsData> result = QueryUtils.fetchNewsData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<NewsData> data) {

            mnewsAdapter.clearAll();

            if (data != null && !data.isEmpty()) {
                mnewsAdapter.addAll(data);
            }
        }
    }



}
