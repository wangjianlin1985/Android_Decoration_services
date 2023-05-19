package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mobileserver.domain.UserInfo;
import com.mobileserver.domain.WashMeal;
import com.mobileserver.util.DB;
import com.mobileserver.util.MapUtils;

public class WashMealDAO {

	public List<WashMeal> QueryWashMeal(String mealName,Timestamp publishDate,String washShopObj) {
		List<WashMeal> washMealList = new ArrayList<WashMeal>();
		DB db = new DB();
		String sql = "select * from WashMeal where 1=1";
		if (!mealName.equals(""))
			sql += " and mealName like '%" + mealName + "%'";
		if(publishDate!=null)
			sql += " and publishDate='" + publishDate + "'";
		if (!washShopObj.equals(""))
			sql += " and washShopObj = '" + washShopObj + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				WashMeal washMeal = new WashMeal();
				washMeal.setMealId(rs.getInt("mealId"));
				washMeal.setMealName(rs.getString("mealName"));
				washMeal.setIntroduce(rs.getString("introduce"));
				washMeal.setPrice(rs.getFloat("price"));
				washMeal.setMealPhoto(rs.getString("mealPhoto"));
				washMeal.setPublishDate(rs.getTimestamp("publishDate"));
				washMeal.setWashShopObj(rs.getString("washShopObj"));
				washMealList.add(washMeal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washMealList;
	}
	
	//�̼Ҳ�ѯ�Լҵ�װ���ײ���Ϣ
	public List<WashMeal> shopQueryWashMeal(String shopUserName) {
		List<WashMeal> washMealList = new ArrayList<WashMeal>();
		DB db = new DB();
		String sql = "select * from WashMeal where 1=1 and washShopObj = '" + shopUserName + "'";
		 
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				WashMeal washMeal = new WashMeal();
				washMeal.setMealId(rs.getInt("mealId"));
				washMeal.setMealName(rs.getString("mealName"));
				washMeal.setIntroduce(rs.getString("introduce"));
				washMeal.setPrice(rs.getFloat("price"));
				washMeal.setMealPhoto(rs.getString("mealPhoto"));
				washMeal.setPublishDate(rs.getTimestamp("publishDate"));
				washMeal.setWashShopObj(rs.getString("washShopObj"));
				washMealList.add(washMeal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washMealList;
	}
	
	
	
	/*�û���ѯ������װ���ײ�*/
	public List<WashMeal> userQueryWashMeal(String userName,String mealName,Timestamp publishDate,int washClassObj,int orderRule) {
		UserInfoDAO userDAO = new UserInfoDAO();
		UserInfo userInfo = userDAO.GetUserInfo(userName);
		
		List<WashMeal> washMealList = new ArrayList<WashMeal>();
		DB db = new DB();
		String sql = "select wm.*,ws.latitude,ws.longitude from WashMeal wm,WashShop ws,WashClass wc where wm.washShopObj=ws.shopUserName and ws.washClassObj=wc.classId ";
		if (!mealName.equals(""))
			sql += " and wm.mealName like '%" + mealName + "%'";
		if(publishDate!=null)
			sql += " and wm.publishDate='" + publishDate + "'";
		if (washClassObj !=0 )
			sql += " and wc.classId = '" + washClassObj + "'";
		  
		//��ѯҪ�û�������װ�޹�˾�����ƾ�γ�Ȳ�ѯ��Χ
		sql += " and ws.latitude > " + (userInfo.getLatitude() - 0.3) + " and ws.latitude < " + (userInfo.getLatitude() + 0.3);
		sql += " and ws.longitude > " + (userInfo.getLongitude() - 0.3) + " and ws.longitude <" + (userInfo.getLongitude() + 0.3);
		  
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				WashMeal washMeal = new WashMeal();
				washMeal.setMealId(rs.getInt("mealId"));
				washMeal.setMealName(rs.getString("mealName"));
				washMeal.setIntroduce(rs.getString("introduce"));
				washMeal.setPrice(rs.getFloat("price"));
				washMeal.setMealPhoto(rs.getString("mealPhoto"));
				washMeal.setPublishDate(rs.getTimestamp("publishDate"));
				washMeal.setWashShopObj(rs.getString("washShopObj"));
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				//��ȡĿ����̺͵�ǰ�û��ľ���
				double distance = MapUtils.GetDistance(userInfo.getLatitude(), userInfo.getLongitude(), latitude, longitude);
				washMeal.setDistance(distance);
				washMealList.add(washMeal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		
		if(orderRule == 1) {
			//���վ�������
			Collections.sort(washMealList, new Comparator<WashMeal>() { 
				public int compare(WashMeal meal1, WashMeal meal2) {
					if (meal1.getDistance() > meal2.getDistance()) return 1;
					return -1;
				}
			});
			
		}
		
		if(orderRule == 2) {
			//���ռ۸�����
			Collections.sort(washMealList, new Comparator<WashMeal>() { 
				public int compare(WashMeal meal1, WashMeal meal2) {
					if (meal1.getPrice() > meal2.getPrice()) return 1;
					return -1;
				}
			});
		}  
		
		return washMealList;
	}
	
	
	
	
	
	
	/* ����װ���ײͶ��󣬽���װ���ײ͵����ҵ�� */
	public String AddWashMeal(WashMeal washMeal) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в�����װ���ײ� */
			String sqlString = "insert into WashMeal(mealName,introduce,price,mealPhoto,publishDate,washShopObj) values (";
			sqlString += "'" + washMeal.getMealName() + "',";
			sqlString += "'" + washMeal.getIntroduce() + "',";
			sqlString += washMeal.getPrice() + ",";
			sqlString += "'" + washMeal.getMealPhoto() + "',";
			sqlString += "'" + washMeal.getPublishDate() + "',";
			sqlString += "'" + washMeal.getWashShopObj() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "װ���ײ���ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ���ײ����ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ��װ���ײ� */
	public String DeleteWashMeal(int mealId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from WashMeal where mealId=" + mealId;
			db.executeUpdate(sqlString);
			result = "װ���ײ�ɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ���ײ�ɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* �����ײ�id��ȡ��װ���ײ� */
	public WashMeal GetWashMeal(int mealId) {
		WashMeal washMeal = null;
		DB db = new DB();
		String sql = "select * from WashMeal where mealId=" + mealId;
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				washMeal = new WashMeal();
				washMeal.setMealId(rs.getInt("mealId"));
				washMeal.setMealName(rs.getString("mealName"));
				washMeal.setIntroduce(rs.getString("introduce"));
				washMeal.setPrice(rs.getFloat("price"));
				washMeal.setMealPhoto(rs.getString("mealPhoto"));
				washMeal.setPublishDate(rs.getTimestamp("publishDate"));
				washMeal.setWashShopObj(rs.getString("washShopObj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washMeal;
	}
	/* ����װ���ײ� */
	public String UpdateWashMeal(WashMeal washMeal) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update WashMeal set ";
			sql += "mealName='" + washMeal.getMealName() + "',";
			sql += "introduce='" + washMeal.getIntroduce() + "',";
			sql += "price=" + washMeal.getPrice() + ",";
			sql += "mealPhoto='" + washMeal.getMealPhoto() + "',";
			sql += "publishDate='" + washMeal.getPublishDate() + "',";
			sql += "washShopObj='" + washMeal.getWashShopObj() + "'";
			sql += " where mealId=" + washMeal.getMealId();
			db.executeUpdate(sql);
			result = "װ���ײ͸��³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ���ײ͸���ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
