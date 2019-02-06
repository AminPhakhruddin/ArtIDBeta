package com.luminaryid.android.artidbeta;

public class Users {

    public String name;
    public String image;
    public String status;
    public String thumb_image;

    public Users(){

    }

    public Users(String name, String image, String status, String thumb_image) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.thumb_image = thumb_image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) { this.status = status; }

    public void setThumb_image(String thumbImage) {
        this.thumb_image = thumbImage;
    }



    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getThumb_image() {
        return thumb_image;
    }


}
