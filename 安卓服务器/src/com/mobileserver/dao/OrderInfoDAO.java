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
	
	//�̼ҵ��̲�ѯ�Լ����̵Ķ���
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
	
	
	
	/* ���붩�����󣬽��ж��������ҵ�� */
	public String AddOrderInfo(OrderInfo orderInfo) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в����¶��� */
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
			result = "������ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "�������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ������ */
	public String DeleteOrderInfo(int orderId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from OrderInfo where orderId=" + orderId;
			db.executeUpdate(sqlString);
			result = "����ɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "����ɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* ���ݶ�����Ż�ȡ������ */
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
	/* ���¶��� */
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
			result = "�������³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "��������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
