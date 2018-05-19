package com.example.krunal.chatapp;

/**
 * Created by krunal on 8/2/17.
 */

public class ChatBean {

    public int id;
    public String message;
    public String time;
    public String image;
    public int user_id;
    public boolean ischecked=false;
    public boolean isSelected = false;

    public ChatBean() {
    }

    public ChatBean(String message,String image,String time,String is_read,int user_id)
    {
        this.message=message;
        this.image=image;
        this.time=time;
        this.is_read=is_read;
        this.user_id=user_id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }




    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public  String is_read;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
