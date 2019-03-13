package com.example.hacker_news_api_app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static boolean mode; // true if temp web page, false if new intent with comments
    public static ArrayList<Article> favorites_list;


    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton fav_button;

    boolean onFav;

    ArrayList<Article> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "OnCreate: started");

        onFav = false;
        mode = true;
        list = new ArrayList<>();
        favorites_list = new ArrayList<>();
        fav_button = (ImageButton) findViewById(R.id.favorites_button);

        // this is setting up the recycler view and giving it a reference so that we may change the contents of it later
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //this adds a divider decoration - just to make it look nicer (not necessary)
        final DividerItemDecoration dividerItemDecorationn = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecorationn.setDrawable(getResources().getDrawable(R.drawable.divider));

        //this calls the api client and the interface to connect to the Google's hacker news api

        try {
            final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
            apiInterface.getTopStories().enqueue(new Callback<List<Integer>>() {
                @Override
                public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                    List<Integer> stories = response.body();
                    //getting the top 10 stories
                    for (int i = 0; i < 20; i++) {
                        assert stories != null;
                        apiInterface.getArticle(stories.get(i)).enqueue(new Callback<Article_Response>() {
                            @Override
                            public void onResponse(Call<Article_Response> call, Response<Article_Response> response) {
                                //The response will have all of the values found in Article_Response.java

                                assert response.body() != null;
                                assert response.body().getTitle() != null;
                                assert response.body().getUrl() != null;
                                if (response.body().getUrl() != null && response.body().getTitle() != null && response.body().getKids() != null) {
                                    String title = response.body().getTitle().toString();
                                    String url = response.body().getUrl().toString();
                                    List<Integer> kids = response.body().getKids();
                                    list.add(new Article(title, url, kids));
                                    NewsAdapter adapter = new NewsAdapter(list);
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
                }

                @Override
                public void onFailure(Call<List<Integer>> call, Throwable t) {

                }
            });
        } catch (NullPointerException e) {
            Log.d(TAG, "NULL POINTER EXCEPTION OCCURRED");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_switch_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.change_mode) {
            mode = !mode;
            Log.d(TAG, "THE MODE HAD CHANGED TO: " + mode);
            Toast.makeText(getApplicationContext(), "SHOW TEMP Webpage?: mode is " + mode, Toast.LENGTH_LONG).show();
            if (item.getIcon().getConstantState() == getResources().getDrawable(android.R.drawable.checkbox_off_background).getConstantState()) {
                item.setIcon(android.R.drawable.checkbox_on_background);
            } else {
                item.setIcon(android.R.drawable.checkbox_off_background);
            }
        } else if (id == R.id.favorites) {
            Log.d(TAG, "FAVORITES HAS BEEN CLICKED");
            if (!onFav) {
                final DividerItemDecoration dividerItemDecorationn = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                dividerItemDecorationn.setDrawable(getResources().getDrawable(R.drawable.divider));
                setTitle("FAVORITES SECTION");
                NewsAdapter adapter = new NewsAdapter(favorites_list);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(dividerItemDecorationn);
                adapter.notifyDataSetChanged();
                onFav = true;
            } else {
                setTitle("Hacker_News_API_APP");
                final DividerItemDecoration dividerItemDecorationn = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                dividerItemDecorationn.setDrawable(getResources().getDrawable(R.drawable.divider));
                NewsAdapter adapter = new NewsAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(dividerItemDecorationn);
                adapter.notifyDataSetChanged();
                onFav = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
