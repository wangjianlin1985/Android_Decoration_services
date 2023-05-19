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
	/* ����װ�޹�˾������󣬽���װ�޹�˾��������ҵ�� */
	public String AddWashClass(WashClass washClass) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в�����װ�޹�˾���� */
			String sqlString = "insert into WashClass(className,classDesc) values (";
			sqlString += "'" + washClass.getClassName() + "',";
			sqlString += "'" + washClass.getClassDesc() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "װ�޹�˾������ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ�޹�˾�������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ��װ�޹�˾���� */
	public String DeleteWashClass(int classId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from WashClass where classId=" + classId;
			db.executeUpdate(sqlString);
			result = "װ�޹�˾����ɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ�޹�˾����ɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* ��������id��ȡ��װ�޹�˾���� */
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
	/* ����װ�޹�˾���� */
	public String UpdateWashClass(WashClass washClass) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update WashClass set ";
			sql += "className='" + washClass.getClassName() + "',";
			sql += "classDesc='" + washClass.getClassDesc() + "'";
			sql += " where classId=" + washClass.getClassId();
			db.executeUpdate(sql);
			result = "װ�޹�˾������³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "װ�޹�˾�������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
