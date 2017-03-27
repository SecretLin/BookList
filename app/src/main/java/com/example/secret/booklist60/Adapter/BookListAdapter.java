package com.example.secret.booklist60.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.secret.booklist60.DataBase.Booklist;
import com.example.secret.booklist60.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Secret on 2016/9/15.
 * 这是书单页的adapter
 */
public class BookListAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Booklist> list = new ArrayList<>();
    public BookListAdapter(Context context){
        this.context = context;
    }
    public void bindData(List<Booklist> list){
        this.list.clear();
        if (list != null){
            this.list.addAll(list);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BookListHolder holder =new BookListHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_booklist,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final BookListHolder bookListHolder = (BookListHolder) holder;
        bookListHolder.bindData(list.get(position));
        Glide.with(context).load(list.get(position).getCover()).
                diskCacheStrategy(DiskCacheStrategy.RESULT).into(bookListHolder.ivCover);

        System.out.println("doubanscore:"+list.get(position).getScore());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Booklist getItem(int position){


        return list.get(position);
    }

}
