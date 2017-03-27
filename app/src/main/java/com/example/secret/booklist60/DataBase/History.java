package com.example.secret.booklist60.DataBase;

import cn.bmob.v3.BmobObject;

/**
 * Created by Secret on 2016/10/11.
 * 历史记录
 */

public class History extends BmobObject {
    private MyUser myUser;
    private Booklist booklist;

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public Booklist getBooklist() {
        return booklist;
    }

    public void setBooklist(Booklist booklist) {
        this.booklist = booklist;
    }


}
