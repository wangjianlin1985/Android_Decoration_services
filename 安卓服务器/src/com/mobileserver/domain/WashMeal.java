package com.mobileserver.domain;

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
    private java.sql.Timestamp publishDate;
    public java.sql.Timestamp getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(java.sql.Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    /*装修公司*/
    private String washShopObj;
    public String getWashShopObj() {
        return washShopObj;
    }
    public void setWashShopObj(String washShopObj) {
        this.washShopObj = washShopObj;
    }
    
    /*用户到装修公司的距离单位：米*/
    private double distance;
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
    

}