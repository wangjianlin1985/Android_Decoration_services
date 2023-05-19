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
	/* 传入套餐评价对象，进行套餐评价的添加业务 */
	public String AddMealEvaluate(MealEvaluate mealEvaluate) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新套餐评价 */
			String sqlString = "insert into MealEvaluate(washMealObj,evaluateContent,userObj,evaluateTime) values (";
			sqlString += mealEvaluate.getWashMealObj() + ",";
			sqlString += "'" + mealEvaluate.getEvaluateContent() + "',";
			sqlString += "'" + mealEvaluate.getUserObj() + "',";
			sqlString += "'" + mealEvaluate.getEvaluateTime() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "套餐评价添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "套餐评价添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除套餐评价 */
	public String DeleteMealEvaluate(int evaluateId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from MealEvaluate where evaluateId=" + evaluateId;
			db.executeUpdate(sqlString);
			result = "套餐评价删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "套餐评价删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据评价id获取到套餐评价 */
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
	/* 更新套餐评价 */
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
			result = "套餐评价更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "套餐评价更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
