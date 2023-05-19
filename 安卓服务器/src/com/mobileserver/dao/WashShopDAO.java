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
	/* 传入装修公司对象，进行装修公司的添加业务 */
	public String AddWashShop(WashShop washShop) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新装修公司 */
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
			result = "装修公司添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "装修公司添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除装修公司 */
	public String DeleteWashShop(String shopUserName) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from WashShop where shopUserName='" + shopUserName + "'";
			db.executeUpdate(sqlString);
			result = "装修公司删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "装修公司删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据装修公司账号获取到装修公司 */
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
	/* 更新装修公司 */
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
			result = "装修公司更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "装修公司更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
