package com.example.android.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private List<NewsData> mNewsList;

    public NewsAdapter(Context mContext, List<NewsData> mNewsList) {
        this.mContext = mContext;
        this.mNewsList = mNewsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final NewsData currentNews = mNewsList.get(i);

        viewHolder.titleTextView.setText(currentNews.getmTitle());
        viewHolder.sectionTextView.setText(currentNews.getmSection());
        viewHolder.dateTextView.setText(formatDate(currentNews.getmDate()));

        if (currentNews.getmAuthor() == null) {
            viewHolder.authorTextView.setVisibility(View.GONE);
        } else {
            viewHolder.authorTextView.setVisibility(View.VISIBLE);
            viewHolder.authorTextView.setText(currentNews.getmAuthor());
        }

        String trailTextHTML = currentNews.getmTrailTextHTML();
        viewHolder.trailTextView.setText(Html.fromHtml(Html.fromHtml(trailTextHTML).toString()));

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                mContext.startActivity(webIntent);
            }
        });

        Picasso.with(mContext.getApplicationContext())
                .load(currentNews.getmThumbnail())
                .into(viewHolder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void clearAll() {
        mNewsList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<NewsData> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    private String formatDate(String dateString) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat = new SimpleDateFormat("h:mm a (yyyy-MM-dd)");
        return simpleDateFormat.format(dateObject);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView sectionTextView;
        private TextView authorTextView;
        private TextView dateTextView;
        private ImageView thumbnailImageView;
        private TextView trailTextView;
        private CardView cardView;


        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_card);
            sectionTextView = itemView.findViewById(R.id.section_card);
            authorTextView = itemView.findViewById(R.id.author_card);
            dateTextView = itemView.findViewById(R.id.date_card);
            thumbnailImageView = itemView.findViewById(R.id.thumbnail_image_card);
            trailTextView = itemView.findViewById(R.id.trail_text_card);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

}
