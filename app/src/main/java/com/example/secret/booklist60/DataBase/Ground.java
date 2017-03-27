package com.example.secret.booklist60.DataBase;

import cn.bmob.v3.BmobObject;

/**
 * Created by Secret on 2016/12/13.
 * 此数据库包括在发现页的提问、分享的评论和读书心得
 */

public class Ground extends BmobObject {
    private MyUser user;
    private Booklist booklist;
    private String content;
    private String type;


    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Booklist getBooklist() {
        return booklist;
    }

    public void setBooklist(Booklist booklist) {
        this.booklist = booklist;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
