package com.example.secret.booklist60.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secret.booklist60.DataBase.Shoucang;
import com.example.secret.booklist60.R;

/**
 * Created by Secret on 2016/10/21.
 */

public class ShoucangHolder extends RecyclerView.ViewHolder {
    private TextView tvBookname,tvAuthor,tvCount;
    public ImageView ivCover;
    public ShoucangHolder(View itemView) {
        super(itemView);
        tvBookname = (TextView) itemView.findViewById(R.id.tvBookname);
        tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        ivCover = (ImageView) itemView.findViewById(R.id.ivCover);
        tvCount = (TextView) itemView.findViewById(R.id.tvCount);
    }
    public void bindData(Shoucang shoucang){
        tvBookname.setText(shoucang.getBooklist().getName());
        tvAuthor.setText(shoucang.getBooklist().getAuthor());
        if (shoucang.getBooklist().getCount()!=null){
            tvCount.setText(shoucang.getBooklist().getCount().toString());
        }
        else {
            tvCount.setText("0");
        }
    }
}