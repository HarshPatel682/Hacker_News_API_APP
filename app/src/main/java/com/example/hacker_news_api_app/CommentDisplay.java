package com.example.hacker_news_api_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.ULocale;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentDisplay extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    List<Integer> kids_list;
    ArrayList<Article> list;

    TextView title;
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_display);

        title = (TextView) findViewById(R.id.title);

        list = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Article parent = (Article) extras.get("Article");
            kids_list = parent.List;
            title.setText(parent.Title);
            Url = parent.Url;
        }



        // this is setting up the recycler view and giving it a reference so that we may change the contents of it later
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final DividerItemDecoration dividerItemDecorationn = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecorationn.setDrawable(getResources().getDrawable(R.drawable.divider));

        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        for (int i = 0; i < kids_list.size(); i++) {
            apiInterface.getComment(kids_list.get(i)).enqueue(new Callback<Article_Response>() {
                @Override
                public void onResponse(Call<Article_Response> call, Response<Article_Response> response) {
                    if (response.body().getText() != null) {
                        String text = response.body().getText().toString();
                        list.add(new Article(text));
                        Log.d("IN Comment DISPLAY", "ABOUT TO ADD THE ADAPTER");
                        CommentAdapter adapter = new CommentAdapter(list);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addItemDecoration(dividerItemDecorationn);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Article_Response> call, Throwable t) {

                }
            });
        }

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                startActivity(browserIntent);
            }
        });
    }
}
