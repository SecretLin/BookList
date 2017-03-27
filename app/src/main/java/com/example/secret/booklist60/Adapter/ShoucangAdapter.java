package com.example.secret.booklist60.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.secret.booklist60.DataBase.Shoucang;
import com.example.secret.booklist60.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Secret on 2016/10/21.
 * 收藏界面的adapter
 */

public class ShoucangAdapter extends RecyclerView.Adapter {
    private List<Shoucang> list = new ArrayList<>();
    private Context context;
    public ShoucangAdapter(Context context){
        this.context = context;
    }
    public void bindData(List<Shoucang> list){
        this.list.clear();
        if (list != null){
            this.list.addAll(list);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShoucangHolder holder =new ShoucangHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booklist,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShoucangHolder shoucangHolder = (ShoucangHolder) holder;
        shoucangHolder.bindData(list.get(position));
        Glide.with(context).load(list.get(position).getBooklist().getCover()).into(shoucangHolder.ivCover);

        System.out.println("doubanscore:"+list.get(position).getBooklist().getScore());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Shoucang getItem(int position){


        return list.get(position);
    }
}
