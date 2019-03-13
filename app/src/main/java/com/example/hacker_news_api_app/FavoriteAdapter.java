package com.example.hacker_news_api_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyviewHolder> {
    private static final String TAG = "FavoriteAdapter";


    private ArrayList<Article> list;
    private Context context;

    public FavoriteAdapter(ArrayList<Article> list) {
        this.list = list;
    }


    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_row, parent, false);
        context = parent.getContext();
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        holder.title.setText(list.get(position).Title);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
