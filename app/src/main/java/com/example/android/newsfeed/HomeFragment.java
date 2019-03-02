package com.example.android.newsfeed;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        List<NewsData> news = new ArrayList<>();
        news.add(new NewsData("Paul Keating says assisted dying 'unacceptable' as " +
                "Victoria debates law", "https://www.theguardian.com/society/2017/oct" +
                "/19/paul-keating-says-assisted-dying-unacceptable-as-victoria-debates-law"));
        recyclerView.setAdapter(new NewsAdapter(getActivity(), news));
        return view;
    }



}
