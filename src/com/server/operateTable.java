package com.server;

import java.sql.ResultSet;

public class operateTable {
	
	operateDB connection = null;
	
	String sql = null;
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
		return false;
	}
	/**
	 * 判断指定名字的用户是否存在
	 * @param name
	 * @return
	 */
	public boolean isUserExist(String name){
		
		return false;
	}
	/**
	 * 根据用户的名字获得它的id
	 * 需要经过isUserExist判断
	 * @param name
	 * @return
	 */
	public ResultSet getUserIDByName(String name){
		
		return null;
	}
	/**
	 * 根据用户id和密码判断登录是否成功
	 * 验证密码是否正确
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean login(String id,String password){
		
		return false;
	}
	/**
	 * 根据用户的id查询project表
	 * 得到所有他创建的project
	 * @param id
	 * @return
	 */
	public ResultSet getProjectSetByUserID(String id){
		
		return null;
	}
	/**
	 * 判断在project表内
	 * id名下
	 * 是否存在名为summary的project
	 * @param id
	 * @param summary
	 * @return
	 */
	public boolean isProjectExistWithSameName(String id,String summary){
		
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
	public boolean addProject(String summary,String start_time,String end_time){
		
		return false;
	}
	/**
	 * 根据时间 
	 * @param id
	 * @param left_time
	 * @param right_time
	 * @return
	 */
	public ResultSet searchProjectByTime(String id,String left_time,String right_time){
		
		return null;
	}
	/**
	 * 指定id的project是否存在
	 * @param id
	 * @return
	 */
	public boolean isProjectExistWithID(String id){
		
		return false;
	}
	/**
	 * 删除指定id的project
	 * 需要判断是否存在
	 * @param id
	 * @return
	 */
	public boolean deleteProjectByID(String id){
		
		return false;
	}
	/**
	 * 清除指定用户id下的
	 * 所有project
	 * @param id
	 * @return
	 */
	public void clearAllProject(String id){
		
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
		
		return null;
	}
	
	/**
	 * 关闭与数据库的连接
	 */
	public void close(){
		if (connection != null){
			this.connection.closeConnecetion();
		}
	}

}
