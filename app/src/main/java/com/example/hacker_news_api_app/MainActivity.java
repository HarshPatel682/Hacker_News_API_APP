package com.example.hacker_news_api_app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Article> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "OnCreate: started");

        // this is setting up the recycler view and giving it a reference so that we may change the contents of it later
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //this adds a divider decoration - just to make it look nicer (not necessary)
        final DividerItemDecoration dividerItemDecorationn = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecorationn.setDrawable(getResources().getDrawable(R.drawable.divider));

        //this calls the apiclient and the interface to connect to the Google's hacker news api
        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        apiInterface.getTopStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> stories = response.body();
                //getting the top 25 stories
                for (int i = 0; i < 25; i++) {
                    apiInterface.getArticle(stories.get(i)).enqueue(new Callback<Article_Response>() {
                        @Override
                        public void onResponse(Call<Article_Response> call, Response<Article_Response> response) {
                            //The response will have all of the values found in Article_Response.java

                            String title = response.body().getTitle().toString();
                            String url = response.body().getUrl().toString();
                            list.add(new Article(title, url));
                            NewsAdapter adapter = new NewsAdapter(list);
                            recyclerView.setAdapter(adapter);
                            recyclerView.addItemDecoration(dividerItemDecorationn);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Article_Response> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });
    }
}
