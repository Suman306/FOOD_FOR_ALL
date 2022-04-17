package com.sgp22.donate.food_manage;

public class Data_rest {
    int image;
    String header,desc,address;

    public Data_rest(int image, String header, String desc, String address) {
        this.image = image;
        this.header = header;
        this.desc = desc;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
