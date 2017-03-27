package com.example.secret.booklist60.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secret.booklist60.DataBase.Booklist;
import com.example.secret.booklist60.R;

/**
 * Created by Secret on 2016/10/14.
 */

public class SearchHolder extends RecyclerView.ViewHolder {
    private TextView tvBookname,tvAuthor,tvCount;
    public ImageView ivCover;

    public SearchHolder(View itemView) {
        super(itemView);
        tvBookname = (TextView) itemView.findViewById(R.id.tvBookname);
        tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        tvCount = (TextView) itemView.findViewById(R.id.tvCount);
        ivCover = (ImageView) itemView.findViewById(R.id.ivCover);

    }
    public void bindData(Booklist booklist){
        tvBookname.setText(booklist.getName());
        tvAuthor.setText(booklist.getAuthor());
        if (booklist.getCount()!=null){
            tvCount.setText(booklist.getCount().toString());
        }



    }
}
