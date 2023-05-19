package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.WashShop;
import com.mobileserver.util.DB;

public class WashShopDAO {

	public List<WashShop> QueryWashShop(String shopUserName,String shopName,int washClassObj,String telephone,Timestamp addDate) {
		List<WashShop> washShopList = new ArrayList<WashShop>();
		DB db = new DB();
		String sql = "select * from WashShop where 1=1";
		if (!shopUserName.equals(""))
			sql += " and shopUserName like '%" + shopUserName + "%'";
		if (!shopName.equals(""))
			sql += " and shopName like '%" + shopName + "%'";
		if (washClassObj != 0)
			sql += " and washClassObj=" + washClassObj;
		if (!telephone.equals(""))
			sql += " and telephone like '%" + telephone + "%'";
		if(addDate!=null)
			sql += " and addDate='" + addDate + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				WashShop washShop = new WashShop();
				washShop.setShopUserName(rs.getString("shopUserName"));
				washShop.setPassword(rs.getString("password"));
				washShop.setShopName(rs.getString("shopName"));
				washShop.setWashClassObj(rs.getInt("washClassObj"));
				washShop.setShopPhoto(rs.getString("shopPhoto"));
				washShop.setTelephone(rs.getString("telephone"));
				washShop.setAddDate(rs.getTimestamp("addDate"));
				washShop.setAddress(rs.getString("address"));
				washShop.setLatitude(rs.getFloat("latitude"));
				washShop.setLongitude(rs.getFloat("longitude"));
				washShopList.add(washShop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washShopList;
	}
	/* ����װ�޹�˾���󣬽���װ�޹�˾�����ҵ�� */
	public String AddWashShop(WashShop washShop) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в�����װ�޹�˾ */
			String sqlString = "insert into WashShop(shopUserName,password,shopName,washClassObj,shopPhoto,telephone,addDate,address,latitude,longitude) values (";
			sqlString += "'" + washShop.getShopUserName() + "',";
			sqlString += "'" + washShop.getPassword() + "',";
			sqlString += "'" + washShop.getShopName() + "',";
			sqlString += washShop.getWashClassObj() + ",";
			sqlString += "'" + washShop.getShopPhoto() + "',";
			sqlString += "'" + washShop.getTelephone() + "',";
			sqlString += "'" + washShop.getAddDate() + "',";
			sqlString += "'" + washShop.getAddress() + "',";
			sqlString += washShop.getLatitude() + ",";
			sqlString += washShop.getLongitude();
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "װ�޹�˾��ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ�޹�˾���ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ��װ�޹�˾ */
	public String DeleteWashShop(String shopUserName) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from WashShop where shopUserName='" + shopUserName + "'";
			db.executeUpdate(sqlString);
			result = "װ�޹�˾ɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ�޹�˾ɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* ����װ�޹�˾�˺Ż�ȡ��װ�޹�˾ */
	public WashShop GetWashShop(String shopUserName) {
		WashShop washShop = null;
		DB db = new DB();
		String sql = "select * from WashShop where shopUserName='" + shopUserName + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				washShop = new WashShop();
				washShop.setShopUserName(rs.getString("shopUserName"));
				washShop.setPassword(rs.getString("password"));
				washShop.setShopName(rs.getString("shopName"));
				washShop.setWashClassObj(rs.getInt("washClassObj"));
				washShop.setShopPhoto(rs.getString("shopPhoto"));
				washShop.setTelephone(rs.getString("telephone"));
				washShop.setAddDate(rs.getTimestamp("addDate"));
				washShop.setAddress(rs.getString("address"));
				washShop.setLatitude(rs.getFloat("latitude"));
				washShop.setLongitude(rs.getFloat("longitude"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washShop;
	}
	/* ����װ�޹�˾ */
	public String UpdateWashShop(WashShop washShop) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update WashShop set ";
			sql += "password='" + washShop.getPassword() + "',";
			sql += "shopName='" + washShop.getShopName() + "',";
			sql += "washClassObj=" + washShop.getWashClassObj() + ",";
			sql += "shopPhoto='" + washShop.getShopPhoto() + "',";
			sql += "telephone='" + washShop.getTelephone() + "',";
			sql += "addDate='" + washShop.getAddDate() + "',";
			sql += "address='" + washShop.getAddress() + "',";
			sql += "latitude=" + washShop.getLatitude() + ",";
			sql += "longitude=" + washShop.getLongitude();
			sql += " where shopUserName='" + washShop.getShopUserName() + "'";
			db.executeUpdate(sql);
			result = "װ�޹�˾���³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ�޹�˾����ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
