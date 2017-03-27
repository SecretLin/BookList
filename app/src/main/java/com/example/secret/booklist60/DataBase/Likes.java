package com.example.secret.booklist60.DataBase;

import cn.bmob.v3.BmobObject;

/**
 * Created by Secret on 2016/10/23.
 * 喜欢/推荐
 */

public class Likes extends BmobObject {
    private MyUser myUser;
    private Booklist booklist;
    private boolean isLike;
    private String BooklistId;
    private Comment comment;

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


    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getBooklistId() {
        return BooklistId;
    }

    public void setBooklistId(String booklistId) {
        BooklistId = booklistId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
