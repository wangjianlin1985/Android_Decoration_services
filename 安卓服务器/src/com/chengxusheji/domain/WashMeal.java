package com.chengxusheji.domain;

import java.sql.Timestamp;
public class WashMeal {
    /*套餐id*/
    private int mealId;
    public int getMealId() {
        return mealId;
    }
    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    /*装修套餐*/
    private String mealName;
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /*套餐说明*/
    private String introduce;
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /*套餐价格*/
    private float price;
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    /*套餐图片*/
    private String mealPhoto;
    public String getMealPhoto() {
        return mealPhoto;
    }
    public void setMealPhoto(String mealPhoto) {
        this.mealPhoto = mealPhoto;
    }

    /*发布日期*/
    private Timestamp publishDate;
    public Timestamp getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    /*装修公司*/
    private WashShop washShopObj;
    public WashShop getWashShopObj() {
        return washShopObj;
    }
    public void setWashShopObj(WashShop washShopObj) {
        this.washShopObj = washShopObj;
    }

}