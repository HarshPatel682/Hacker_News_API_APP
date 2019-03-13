package com.example.hacker_news_api_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyviewHolder> {
    private static final String TAG = "NewsAdapter";


    private ArrayList<Article> list;
    private Context context;

    public NewsAdapter(ArrayList<Article> list) {
        this.list = list;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_row, parent, false);
        context = parent.getContext();
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {

        holder.fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.fav_button.getDrawable().getConstantState() == context.getResources().getDrawable(android.R.drawable.btn_star_big_off).getConstantState()) {
                    //if the holders button is not clicked, change its value and add it to Star_ON
                    holder.fav_button.setImageResource(android.R.drawable.btn_star_big_on);
                    MainActivity.favorites_list.add(list.get(position));
                    Log.d(TAG, "ADDED: " + list.get(position).Title + " to the favorites list");
                } else {
                    //remove from fav list
                    holder.fav_button.setImageResource(android.R.drawable.btn_star_big_off);
                    MainActivity.favorites_list.remove(list.get(position));
                    Log.d(TAG, "REMOVED: " + list.get(position).Title + " to the favorites list");

                }
            }
        });


        //this will show a temp web page
        holder.title.setText(list.get(position).Title);
        holder.title.setOnClickListener(new View.OnClickListener() {

            /* MIGHT NEED TO CHANGE THIS */

            @Override
            public void onClick(View v) {
                if (MainActivity.mode) {
                    //show a temp page
                    String url = list.get(position).Url;
                    Log.d(TAG, "User Clicked On: " + url + "!");

                    WebView wv = new WebView(context);
                    wv.loadUrl(url);
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });
                    alert.setView(wv);
                    alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.gravity = Gravity.CENTER;
                    dialog.getWindow().setAttributes(lp);
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    LinearLayout parent = (LinearLayout) positiveButton.getParent();
                    parent.setGravity(Gravity.CENTER_HORIZONTAL);
                    View leftSpacer = parent.getChildAt(1);
                    leftSpacer.setVisibility(View.GONE);
                } else {
                    //show the title with comments
                    Intent intent = new Intent(context, CommentDisplay.class);
                    intent.putExtra("Article", list.get(position));

                    Log.d("on click INTENT", "about to change over to commment display");
                    context.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageButton fav_button;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textview);
            fav_button = (ImageButton) itemView.findViewById(R.id.favorites_button);
        }
    }

}
