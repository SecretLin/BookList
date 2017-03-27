package com.example.secret.booklist60.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secret.booklist60.DataBase.History;
import com.example.secret.booklist60.R;

/**
 * Created by Secret on 2016/9/16.
 */
public class HistoryRecordHolder extends RecyclerView.ViewHolder {
    private TextView tvBookname,tvAuthor,tvCount;
    public ImageView ivCover;
    public HistoryRecordHolder(View itemView) {
        super(itemView);
        tvBookname = (TextView) itemView.findViewById(R.id.tvBookname);
        tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        tvCount = (TextView) itemView.findViewById(R.id.tvCount);
        ivCover = (ImageView) itemView.findViewById(R.id.ivCover);
    }
    public void bindData(History history){
        tvBookname.setText(history.getBooklist().getName());
        tvAuthor.setText(history.getBooklist().getAuthor());
        if (history.getBooklist().getCount()!=null){
            tvCount.setText(history.getBooklist().getCount().toString());
        }
        else {
            tvCount.setText("0");
        }



    }
}
