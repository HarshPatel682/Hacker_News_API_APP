package com.example.hacker_news_api_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyviewHolder> {
    private static final String TAG = "CommentAdapter";


    private ArrayList<Article> list;
    private Context context;

    public CommentAdapter(ArrayList<Article> list) {
        this.list = list;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comment_display, parent, false);
        context = parent.getContext();
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {

        //this will show a temp web page
        holder.title.setText(list.get(position).Title);
        holder.title.setTextSize(20);
        holder.title.setTextColor(context.getResources().getColor(android.R.color.white));
//        holder.title.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                    //show a temp page
//                    String url = list.get(position).Url;
//                    Log.d(TAG, "User Clicked On: " + url + "!");
//
//                    WebView wv = new WebView(context);
//                    wv.loadUrl(url);
//                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
//                    wv.setWebViewClient(new WebViewClient() {
//                        @Override
//                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                            view.loadUrl(url);
//                            return true;
//                        }
//                    });
//                    alert.setView(wv);
//                    alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog dialog = alert.create();
//                    dialog.show();
//                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                    lp.copyFrom(dialog.getWindow().getAttributes());
//                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//                    lp.gravity = Gravity.CENTER;
//                    dialog.getWindow().setAttributes(lp);
//                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//                    LinearLayout parent = (LinearLayout) positiveButton.getParent();
//                    parent.setGravity(Gravity.CENTER_HORIZONTAL);
//                    View leftSpacer = parent.getChildAt(1);
//                    leftSpacer.setVisibility(View.GONE);
//
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}
