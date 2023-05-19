package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.MealEvaluate;
import com.mobileserver.util.DB;

public class MealEvaluateDAO {

	public List<MealEvaluate> QueryMealEvaluate(int washMealObj,String userObj,String evaluateTime) {
		List<MealEvaluate> mealEvaluateList = new ArrayList<MealEvaluate>();
		DB db = new DB();
		String sql = "select * from MealEvaluate where 1=1";
		if (washMealObj != 0)
			sql += " and washMealObj=" + washMealObj;
		if (!userObj.equals(""))
			sql += " and userObj = '" + userObj + "'";
		if (!evaluateTime.equals(""))
			sql += " and evaluateTime like '%" + evaluateTime + "%'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				MealEvaluate mealEvaluate = new MealEvaluate();
				mealEvaluate.setEvaluateId(rs.getInt("evaluateId"));
				mealEvaluate.setWashMealObj(rs.getInt("washMealObj"));
				mealEvaluate.setEvaluateContent(rs.getString("evaluateContent"));
				mealEvaluate.setUserObj(rs.getString("userObj"));
				mealEvaluate.setEvaluateTime(rs.getString("evaluateTime"));
				mealEvaluateList.add(mealEvaluate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return mealEvaluateList;
	}
	/* �����ײ����۶��󣬽����ײ����۵����ҵ�� */
	public String AddMealEvaluate(MealEvaluate mealEvaluate) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в������ײ����� */
			String sqlString = "insert into MealEvaluate(washMealObj,evaluateContent,userObj,evaluateTime) values (";
			sqlString += mealEvaluate.getWashMealObj() + ",";
			sqlString += "'" + mealEvaluate.getEvaluateContent() + "',";
			sqlString += "'" + mealEvaluate.getUserObj() + "',";
			sqlString += "'" + mealEvaluate.getEvaluateTime() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "�ײ�������ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "�ײ��������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ���ײ����� */
	public String DeleteMealEvaluate(int evaluateId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from MealEvaluate where evaluateId=" + evaluateId;
			db.executeUpdate(sqlString);
			result = "�ײ�����ɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "�ײ�����ɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* ��������id��ȡ���ײ����� */
	public MealEvaluate GetMealEvaluate(int evaluateId) {
		MealEvaluate mealEvaluate = null;
		DB db = new DB();
		String sql = "select * from MealEvaluate where evaluateId=" + evaluateId;
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				mealEvaluate = new MealEvaluate();
				mealEvaluate.setEvaluateId(rs.getInt("evaluateId"));
				mealEvaluate.setWashMealObj(rs.getInt("washMealObj"));
				mealEvaluate.setEvaluateContent(rs.getString("evaluateContent"));
				mealEvaluate.setUserObj(rs.getString("userObj"));
				mealEvaluate.setEvaluateTime(rs.getString("evaluateTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return mealEvaluate;
	}
	/* �����ײ����� */
	public String UpdateMealEvaluate(MealEvaluate mealEvaluate) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update MealEvaluate set ";
			sql += "washMealObj=" + mealEvaluate.getWashMealObj() + ",";
			sql += "evaluateContent='" + mealEvaluate.getEvaluateContent() + "',";
			sql += "userObj='" + mealEvaluate.getUserObj() + "',";
			sql += "evaluateTime='" + mealEvaluate.getEvaluateTime() + "'";
			sql += " where evaluateId=" + mealEvaluate.getEvaluateId();
			db.executeUpdate(sql);
			result = "�ײ����۸��³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "�ײ����۸���ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
