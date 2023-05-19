package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.OrderInfo;
import com.mobileserver.util.DB;

public class OrderInfoDAO {

	public List<OrderInfo> QueryOrderInfo(int washMealObj,String userObj,String telephone,String orderTime,int orderState) {
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		DB db = new DB();
		String sql = "select * from OrderInfo where 1=1";
		if (washMealObj != 0)
			sql += " and washMealObj=" + washMealObj;
		if (!userObj.equals(""))
			sql += " and userObj = '" + userObj + "'";
		if (!telephone.equals(""))
			sql += " and telephone like '%" + telephone + "%'";
		if (!orderTime.equals(""))
			sql += " and orderTime like '%" + orderTime + "%'";
		if (orderState != 0)
			sql += " and orderState=" + orderState;
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getInt("orderId"));
				orderInfo.setWashMealObj(rs.getInt("washMealObj"));
				orderInfo.setOrderCount(rs.getInt("orderCount"));
				orderInfo.setUserObj(rs.getString("userObj"));
				orderInfo.setTelephone(rs.getString("telephone"));
				orderInfo.setOrderTime(rs.getString("orderTime"));
				orderInfo.setOrderState(rs.getInt("orderState"));
				orderInfo.setMemo(rs.getString("memo"));
				orderInfoList.add(orderInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return orderInfoList;
	}
	
	//商家店铺查询自己店铺的订单
	public List<OrderInfo> shopQueryOrderInfo(String shopUserName,int washMealObj,String userObj,String telephone,String orderTime,int orderState) {
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		DB db = new DB();
		String sql = "select OrderInfo.* from OrderInfo,WashMeal where OrderInfo.washMealObj = WashMeal.mealId and washMeal.washShopObj='" + shopUserName + "'";
		if (washMealObj != 0)
			sql += " and OrderInfo.washMealObj=" + washMealObj;
		if (!userObj.equals(""))
			sql += " and OrderInfo.userObj = '" + userObj + "'";
		if (!telephone.equals(""))
			sql += " and OrderInfo.telephone like '%" + telephone + "%'";
		if (!orderTime.equals(""))
			sql += " and OrderInfo.orderTime like '%" + orderTime + "%'";
		if (orderState != 0)
			sql += " and OrderInfo.orderState=" + orderState;
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getInt("orderId"));
				orderInfo.setWashMealObj(rs.getInt("washMealObj"));
				orderInfo.setOrderCount(rs.getInt("orderCount"));
				orderInfo.setUserObj(rs.getString("userObj"));
				orderInfo.setTelephone(rs.getString("telephone"));
				orderInfo.setOrderTime(rs.getString("orderTime"));
				orderInfo.setOrderState(rs.getInt("orderState"));
				orderInfo.setMemo(rs.getString("memo"));
				orderInfoList.add(orderInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return orderInfoList;
	}
	
	
	
	/* 传入订单对象，进行订单的添加业务 */
	public String AddOrderInfo(OrderInfo orderInfo) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新订单 */
			String sqlString = "insert into OrderInfo(washMealObj,orderCount,userObj,telephone,orderTime,orderState,memo) values (";
			sqlString += orderInfo.getWashMealObj() + ",";
			sqlString += orderInfo.getOrderCount() + ",";
			sqlString += "'" + orderInfo.getUserObj() + "',";
			sqlString += "'" + orderInfo.getTelephone() + "',";
			sqlString += "'" + orderInfo.getOrderTime() + "',";
			sqlString += orderInfo.getOrderState() + ",";
			sqlString += "'" + orderInfo.getMemo() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "订单添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "订单添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除订单 */
	public String DeleteOrderInfo(int orderId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from OrderInfo where orderId=" + orderId;
			db.executeUpdate(sqlString);
			result = "订单删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "订单删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据订单编号获取到订单 */
	public OrderInfo GetOrderInfo(int orderId) {
		OrderInfo orderInfo = null;
		DB db = new DB();
		String sql = "select * from OrderInfo where orderId=" + orderId;
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getInt("orderId"));
				orderInfo.setWashMealObj(rs.getInt("washMealObj"));
				orderInfo.setOrderCount(rs.getInt("orderCount"));
				orderInfo.setUserObj(rs.getString("userObj"));
				orderInfo.setTelephone(rs.getString("telephone"));
				orderInfo.setOrderTime(rs.getString("orderTime"));
				orderInfo.setOrderState(rs.getInt("orderState"));
				orderInfo.setMemo(rs.getString("memo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return orderInfo;
	}
	/* 更新订单 */
	public String UpdateOrderInfo(OrderInfo orderInfo) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update OrderInfo set ";
			sql += "washMealObj=" + orderInfo.getWashMealObj() + ",";
			sql += "orderCount=" + orderInfo.getOrderCount() + ",";
			sql += "userObj='" + orderInfo.getUserObj() + "',";
			sql += "telephone='" + orderInfo.getTelephone() + "',";
			sql += "orderTime='" + orderInfo.getOrderTime() + "',";
			sql += "orderState=" + orderInfo.getOrderState() + ",";
			sql += "memo='" + orderInfo.getMemo() + "'";
			sql += " where orderId=" + orderInfo.getOrderId();
			db.executeUpdate(sql);
			result = "订单更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "订单更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
