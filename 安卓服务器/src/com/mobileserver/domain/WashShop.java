package com.mobileserver.domain;

public class WashShop {
    /*装修公司账号*/
    private String shopUserName;
    public String getShopUserName() {
        return shopUserName;
    }
    public void setShopUserName(String shopUserName) {
        this.shopUserName = shopUserName;
    }

    /*登录密码*/
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /*装修公司名称*/
    private String shopName;
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /*装修公司种类*/
    private int washClassObj;
    public int getWashClassObj() {
        return washClassObj;
    }
    public void setWashClassObj(int washClassObj) {
        this.washClassObj = washClassObj;
    }

    /*装修公司照片*/
    private String shopPhoto;
    public String getShopPhoto() {
        return shopPhoto;
    }
    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    /*店家电话*/
    private String telephone;
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /*入驻日期*/
    private java.sql.Timestamp addDate;
    public java.sql.Timestamp getAddDate() {
        return addDate;
    }
    public void setAddDate(java.sql.Timestamp addDate) {
        this.addDate = addDate;
    }

    /*店铺地址*/
    private String address;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    /*纬度*/
    private float latitude;
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /*经度*/
    private float longitude;
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}