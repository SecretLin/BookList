package com.example.secret.booklist60.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.secret.booklist60.DataBase.MyUser;
import com.example.secret.booklist60.ExitApplication;
import com.example.secret.booklist60.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobUser;

/**
 * Created by Secret on 2016/9/13.
 * 首页
 */
public class HomeActivity extends Activity {
    Button btnRegist,btnLogin;
    ImageView ivQQ,ivSina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //登录按钮
        btnLogin = (Button) findViewById(R.id.btnLogin_Home);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(HomeActivity.this,LoginActivity.class));

            }
        });
        //注册按钮
        btnRegist = (Button) findViewById(R.id.btnRegist_Home);
        btnRegist.getBackground().mutate().setAlpha(50);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,RegistActivity.class));
            }
        });
        //获取当前用户，下次打开该app就可以直接登录
        MyUser currentUser = BmobUser.getCurrentUser(this,MyUser.class);
        if (currentUser!=null){
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            finish();
        }

//        /*
//        以下为第三方账号登录
//         */
//        ShareSDK.initSDK(HomeActivity.this);
////        EventBus.getDefault().register(this);
//
//        ivQQ = (ImageView) findViewById(R.id.ivQQlogin);
//        //使用qq登录
//        ivQQ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ShareSDKUtils.Login(QQ.NAME);
//
//
//            }
//        });
//
//        ivSina = (ImageView) findViewById(R.id.ivSinaLogin);
//        ivSina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShareSDKUtils.Login(SinaWeibo.NAME);
//            }
//        });


        ExitApplication.getInstance().addActivities(this);
    }


//    public void onEventMainThread(MapEvent event) {
//        final ThirdPartyUser user = new ThirdPartyUser();
//        user.setUserName(event.getMap().get("username"));
//        user.setUserId(event.getMap().get("id"));
//        user.setAvatar(event.getMap().get("avatar"));
//        user.save(this, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(HomeActivity.this,"登录成功....",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("thirdUser",user);
//                intent.putExtra("isThirdParty",true);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Toast.makeText(HomeActivity.this,"登录失败,"+s,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//    }

    /*
    双击退出程序
     */
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ExitApplication.getInstance().exit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
