package com.example.secret.booklist60.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.secret.booklist60.Adapter.ShoucangAdapter;
import com.example.secret.booklist60.DataBase.MyUser;
import com.example.secret.booklist60.DataBase.Shoucang;
import com.example.secret.booklist60.ExitApplication;
import com.example.secret.booklist60.R;
import com.example.secret.booklist60.RecyclerItemClickListener;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
/*
   收藏界面
 */
public class ShoucangActivity extends Activity {
    RecyclerView rv;
    LinearLayoutManager layoutManager;
    ShoucangAdapter adapter;
    ImageButton btnBack;
    TextView tvActionbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);

        tvActionbar = (TextView) findViewById(R.id.tvTitle);
        tvActionbar.setText("收藏");

        rv = (RecyclerView) findViewById(R.id.rv_Shoucang);
        adapter = new ShoucangAdapter(ShoucangActivity.this);
        rv.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(this, rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                Intent intent = new Intent(ShoucangActivity.this, BookListDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Booklist", adapter.getItem(position));
                intent.putExtras(bundle);


                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        boolean isCurrentUser = getIntent().getBooleanExtra("isCurrentUser",true);
        if (isCurrentUser){
            MyUser myUser = BmobUser.getCurrentUser(this,MyUser.class);
            isQuery(myUser);
        }
        else {
            MyUser user = (MyUser) getIntent().getSerializableExtra("user");
            isQuery(user);
        }

        ExitApplication.getInstance().addActivities(this);
    }
    //查询书本，如果收藏者为当前用户的，则获取出来
    public void isQuery(MyUser myUser) {


        BmobQuery<Shoucang> query = new BmobQuery<>();
        query.addWhereEqualTo("myUser",new BmobPointer(myUser));
        query.order("-createdAt");
        query.include("booklist");
        query.findObjects(ShoucangActivity.this, new FindListener<Shoucang>() {
            @Override
            public void onSuccess(List<Shoucang> list) {
                if (list.size()!=0){
                    adapter.bindData(list);
                    adapter.notifyDataSetChanged();
                }
                else {
                    rv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
