package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.WashClass;
import com.mobileserver.util.DB;

public class WashClassDAO {

	public List<WashClass> QueryWashClass() {
		List<WashClass> washClassList = new ArrayList<WashClass>();
		DB db = new DB();
		String sql = "select * from WashClass where 1=1";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				WashClass washClass = new WashClass();
				washClass.setClassId(rs.getInt("classId"));
				washClass.setClassName(rs.getString("className"));
				washClass.setClassDesc(rs.getString("classDesc"));
				washClassList.add(washClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washClassList;
	}
	/* 传入装修公司种类对象，进行装修公司种类的添加业务 */
	public String AddWashClass(WashClass washClass) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新装修公司种类 */
			String sqlString = "insert into WashClass(className,classDesc) values (";
			sqlString += "'" + washClass.getClassName() + "',";
			sqlString += "'" + washClass.getClassDesc() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "装修公司种类添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "装修公司种类添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除装修公司种类 */
	public String DeleteWashClass(int classId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from WashClass where classId=" + classId;
			db.executeUpdate(sqlString);
			result = "装修公司种类删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "装修公司种类删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据种类id获取到装修公司种类 */
	public WashClass GetWashClass(int classId) {
		WashClass washClass = null;
		DB db = new DB();
		String sql = "select * from WashClass where classId=" + classId;
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				washClass = new WashClass();
				washClass.setClassId(rs.getInt("classId"));
				washClass.setClassName(rs.getString("className"));
				washClass.setClassDesc(rs.getString("classDesc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return washClass;
	}
	/* 更新装修公司种类 */
	public String UpdateWashClass(WashClass washClass) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update WashClass set ";
			sql += "className='" + washClass.getClassName() + "',";
			sql += "classDesc='" + washClass.getClassDesc() + "'";
			sql += " where classId=" + washClass.getClassId();
			db.executeUpdate(sql);
			result = "装修公司种类更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "装修公司种类更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
