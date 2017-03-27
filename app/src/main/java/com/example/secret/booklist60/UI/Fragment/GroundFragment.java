package com.example.secret.booklist60.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.secret.booklist60.Adapter.GroundAdapter;
import com.example.secret.booklist60.DataBase.Booklist;
import com.example.secret.booklist60.DataBase.Ground;
import com.example.secret.booklist60.UI.BookListDetailActivity;
import com.example.secret.booklist60.UI.GroundPinLunActivity;
import com.example.secret.booklist60.R;
import com.example.secret.booklist60.RecyclerItemClickListener;
import com.example.secret.booklist60.UI.AddQuestionActivity;
import com.example.secret.booklist60.UI.AddShareActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Secret on 2016/8/27.
 * 这是一个发现页面
 */
public class GroundFragment extends Fragment {
    private ViewPager vp;
    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    /*线程安全中的一种原子加减
       初始化what为0，代表在第一个位置
     */
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;
    private SwipeRefreshLayout refreshLayout;

//    private FloatingActionButton btnAdd;
    private RecyclerView rv;
    private GroundAdapter adapter;
    public static FloatingActionMenu bigFAB;
    private FloatingActionButton btnQuestion,btnShare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ground, container, false);
        init(view);
        initViewPager(view);


        return view;
    }

    private void init(View view) {


        rv = (RecyclerView) view.findViewById(R.id.rv);
        adapter = new GroundAdapter(getContext());
        query();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),GroundPinLunActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Ground",adapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        bigFAB = (FloatingActionMenu) view.findViewById(R.id.bigFAB);
        bigFAB.setClosedOnTouchOutside(true);
        btnQuestion = (FloatingActionButton) view.findViewById(R.id.btnQuestion);
        btnShare = (FloatingActionButton) view.findViewById(R.id.btnShare);
        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddQuestionActivity.class));
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddShareActivity.class));
            }
        });


        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void query() {
        BmobQuery<Ground> query = new BmobQuery<>();
        query.include("user,booklist");
        query.order("-createdAt");
        query.findObjects(getContext(), new FindListener<Ground>() {
            @Override
            public void onSuccess(List<Ground> list) {
                adapter.bindData(list);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        query();
        if (bigFAB.isOpened()){
            bigFAB.close(true);
        }
    }
    private void initViewPager(View view) {
        vp = (ViewPager) view.findViewById(R.id.vp);
        ViewGroup group = (ViewGroup) view.findViewById(R.id.layout_point);//LinearLayout布局

        //      这里存放的是三张广告背景
        List<View> advPics = new ArrayList<View>();

        ImageView img1 = new ImageView(getContext());
        img1.setBackgroundResource(R.mipmap.activity_one);
        advPics.add(img1);

        ImageView img2 = new ImageView(getContext());
        img2.setBackgroundResource(R.mipmap.activity_two);
        advPics.add(img2);

        ImageView img3 = new ImageView(getContext());
        img3.setBackgroundResource(R.mipmap.activity_three);
        advPics.add(img3);


        //初始化三个小白点
        imageViews = new ImageView[advPics.size()];
        for (int i = 0; i < advPics.size(); i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));//三个小白点的大小
            imageView.setPadding(5, 5, 5, 5);//整个linearlayout布局上下左右相差5px
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.vp_point_selected);
            } else {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.vp_point_unselected);
            }
            group.addView(imageViews[i]);//将三个小白点添加到LinearLayout中
        }

        vp.setAdapter(new AdvAdapter(advPics));
        vp.setOnPageChangeListener(new GuidePageChangeListener());
        vp.setOnTouchListener(new View.OnTouchListener() {
         //设置滑动事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;//如果按下滑动，那么就不会自动滑动
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                //不断循环，先把获取到的传给handler，告诉它现在在第几张图，然后继续下一张图，停三秒后才显示
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();//对取得的值进行操作，并休眠3秒后继续执行
                    }
                }
            }

        }).start();
    }


    private void whatOption() {
        what.incrementAndGet();//加一后再取值，类似于++i
        if (what.get() > imageViews.length - 1) {//如果取得的值大于2
            what.getAndAdd(-3);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
    }

    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            vp.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            what.getAndSet(arg0);
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.mipmap.vp_point_selected);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.mipmap.vp_point_unselected);
                }
            }

        }

    }

    private final class AdvAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }
    public void toast(String s){
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }


}
