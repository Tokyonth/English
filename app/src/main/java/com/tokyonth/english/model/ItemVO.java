package com.tokyonth.english.model;

public class ItemVO {

    private String mImg;
    private String mName;

    public ItemVO(String mImg, String mName) {
        this.mImg = mImg;
        this.mName = mName;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImg() {
        return mImg;
    }

    public String getmName() {
        return mName;
    }

}
