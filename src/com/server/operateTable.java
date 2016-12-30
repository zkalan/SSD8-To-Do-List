package com.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class operateTable {
	
	private operateDB connection = null;
	
	private String sql = null;
	/**
	 * 初始化对数据库的连接
	 */
	public operateTable(){
		this.connection = new operateDB();
		this.sql = null;
	}
	/**
	 * 向user表添加用户
	 * 需要经过isUserExist判断
	 * 成功返回1
	 * 失败返回-1
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean addUser(String name,String password){
		//构造插入的sql语句
		this.sql = "insert into user(user_name,user_passwd) "
				+ "values ('" + name + "','" + password + "');";
		//验证操作结果
		if (this.connection.updateSQL(this.sql) == 1) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断指定名字的用户是否存在
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public boolean isUserExist(String name) throws SQLException{
		
		if (false == this.getResultSetWithSignalCondition("user", "user_name", name).next()) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 根据用户的名字获得它的id
	 * 需要经过isUserExist判断
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public String getUserIDByName(String name) throws SQLException{
		
		ResultSet res = this.getResultSetWithSignalCondition("user", "user_name", name);
		
		String id = null;
		
		while (res.next()) {
			id = res.getString(1);
		}
		
		return id;
	}
	/**
	 * 根据用户id和密码判断登录是否成功
	 * 验证密码是否正确
	 * @param id
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public boolean login(String id,String password) throws SQLException{
		
		ResultSet res = this.getResultSetWithSignalCondition("user", "user_id", id);
		
		while (res.next()) {
			if (res.getString(3).equals(password)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	/**
	 * 根据用户的id查询project表
	 * 得到所有他创建的project
	 * @param id
	 * @return
	 */
	public ResultSet getProjectSetByUserID(String id){
		
		return this.getResultSetWithSignalCondition("project", "project_user_id", id);
	}
	/**
	 * 判断在project表内
	 * id名下
	 * 是否存在名为title的project
	 * @param id
	 * @param summary
	 * @return
	 * @throws SQLException 
	 */
	public boolean isProjectExistWithSameName(String id,String title) throws SQLException{
		
		ResultSet res = this.getProjectSetByUserID(id);
		
		while (res.next()) {
			if (title.equals(res.getString(3))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 向project表内插入数据
	 * 需要验证project是否存在
	 * @param summary
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public boolean addProject(String id,String title,String start_time,String end_time,String summary){
		
		this.sql = "insert into project (project_user_id,project_title,project_start_time,project_end_time,project_summary) "
				+ "values ('" + id + "','" + title + "','" + start_time + "','" + end_time + "','" + summary + "');";
		
		if (1 == this.connection.updateSQL(this.sql)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 根据时间 
	 * @param id
	 * @param left_time
	 * @param right_time
	 * @return
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public ResultSet searchProjectByTime(String id,String left_time,String right_time) throws ParseException, SQLException{
		
		ResultSet res = this.getProjectSetByUserID(id);
		
		while (res.next()) {
			if (!this.inTimeRange(left_time, res.getString(4), res.getString(5), right_time)) {
				res.deleteRow();
			}
		}
		return res;
	}
	/**
	 * 指定id的project是否存在
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public boolean isProjectExistWithID(String id) throws SQLException{
		
		if (false == this.getResultSetWithSignalCondition("project", "project_id", id).next()) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 删除指定id的project
	 * 需要判断是否存在
	 * @param id
	 * @return
	 */
	public boolean deleteProjectByID(String id){
		
		this.sql = "delete project from project where project_id='" + id + "';";
		
		if (1 == this.connection.updateSQL(this.sql)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 清除指定用户id下的
	 * 所有project
	 * @param id
	 * @return
	 */
	public boolean clearAllProject(String id){
		
		this.sql = "delete project from project where project_user_id='" + id + "';";
		
		if (1 == this.connection.updateSQL(this.sql)) {
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * 指定表内
	 * 获得满足单一条件
	 * 的结果集合
	 * @param table_name
	 * @param column
	 * @param condition
	 * @return
	 */
	public ResultSet getResultSetWithSignalCondition(String table_name,String column,String condition){
		
		this.sql = "select * from " + table_name + " where " + column + "='" + condition + "';";
		
		ResultSet res = this.connection.executeSQL(this.sql);
		
		return res;
	}
	
	/**
	 * 关闭与数据库的连接
	 */
	public void close(){
		if (connection != null){
			this.connection.closeConnecetion();
		}
	}
	
	/**
	 * 指定的时间是否位于指定的时间段内
	 * @param left_time
	 * @param start_time
	 * @param end_time
	 * @param right_time
	 * @return
	 * @throws java.text.ParseException
	 */
	public boolean inTimeRange(String left_time,String start_time,String end_time,String right_time) throws java.text.ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		//
		Date left = null , right = null;
		//
		Date start = null , end = null;
		
		left = sdf.parse(left_time);
		start = sdf.parse(start_time);
		end = sdf.parse(end_time);
		right = sdf.parse(right_time);
		
		if (left.getTime() <= start.getTime() && right.getTime() >= end.getTime()) {
			return true;
		} else {
			return false;
		}
	}

}
