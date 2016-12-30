package com.server;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
/**
 * 接口的具体实现
 * @author zk
 *
 */
@WebService
public class ListService implements ListServiceInterface{
	
	//被隐藏的方法
	//@WebMethod(exclude=true)
	
	operateTable operatetable = null;
	/**
	 * 初始化
	 */
	public ListService(){
		this.operatetable = new operateTable();
	}
	

	/**
	 * 根据用户提供的用户名和密码进行注册
	 * 首先验证该用户名是否已经被注册
	 * @author zk
	 * @param name the user input name
	 * @param password the user input password 
	 * @throws SQLException 
	 */
	@WebMethod
	public String register(String name, String password) throws Exception{
		if (!operatetable.isUserExist(name)){
			
			StringBuilder res = new StringBuilder();
			//用户名不存在，可以注册
			if (operatetable.addUser(name, password)) {
				//返回用户的信息
				res.append("User register success.\n");
				
				res.append("User-ID:" + operatetable.getUserIDByName(name) + "\n");
				res.append("User-Name:" + name + "\n");
				res.append("User-Password:" + password + "\n");
			}
			return res.toString();
		} else {
			//用户名已存在
			StringBuilder res = new StringBuilder();
			res.append("The Name already exists.\n");
			
			return res.toString();
		}
	}

	/**
	 * 对用户输入的信息进行登录验证
	 * 需要首先检测用户名是否存在
	 * @author zk
	 * @param name 用户输入的名字
	 * @param password 用户输入的密码
	 * @throws SQLException 
	 */
	@WebMethod
	public boolean login(String name, String password) throws Exception {
		
		if (operatetable.isUserExist(name)) {
			//用户名存在，可以进行登录验证
			if (operatetable.verifyNameAndPassword(operatetable.getUserIDByName(name), password)) {
				//登录成功
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 列出用户名下的所有项目
	 * 需要验证用户是否合法
	 * @author zk
	 * @param name
	 * @param password
	 */
	@WebMethod
	public String showProject(String name, String password) throws Exception {
		
		StringBuilder res = new StringBuilder();
		
		if (this.login(name,password)) {
			//查询获得项目的集合
			ResultSet projectSet = operatetable.getProjectSetByUserID(operatetable.getUserIDByName(name));
			
			if (false == projectSet.next()) {
				return "There are currently no projects in your list.\n";
			} else {
				
				//移动游标到第一行之前
				projectSet.beforeFirst();
				
				while (projectSet.next()) {
					res.append(projectSet.getString(1) + "/");
					res.append(projectSet.getString(3) + "/");
					res.append(projectSet.getString(4) + "/");
					res.append(projectSet.getString(5) + "/");
					res.append(projectSet.getString(6) + "\n");
				}
			}
		} else {
			return "Insufficient authority.\n";
		}
		return res.toString();
	}

	/**
	 * 添加项目
	 * 需要验证用户名和密码的正确性
	 * @author zk
	 * @param name 用户名
	 * @param password 用户密码
	 * @param title 项目标题
	 * @param start_time 项目开始时间 yyyy-MM-dd hh:mm:ss
	 * @param end_time 项目结束时间 yyyy-MM-dd hh:mm:ss
	 * @param summary 
	 * @throws SQLException 
	 */
	@WebMethod
	public String addProject(String name, String password, String title, String start_time, String end_time,String summary) throws Exception {
		
		StringBuilder res = new StringBuilder();
		
		if (this.login(name,password)) {
			//账号验证成功
			if (operatetable.addProject(operatetable.getUserIDByName(name),title, start_time, end_time, summary)){
				res.append("Add project success.\n");
			} else {
				res.append("Add project failure.\n");
			}
		} else {
			return "Insufficient authority.\n";
		}
		return res.toString();
	}

	/**
	 * 返回满足时间范围条件的项目信息
	 * 验证用户信息
	 * @author zk
	 * @param name
	 * @param password
	 * @param left_time
	 * @param right_time
	 * return boolean
	 */
	@WebMethod
	public String searchProjectByTime(String name, String password, String left_time, String right_time) throws Exception, java.text.ParseException {
		
		StringBuilder res = new StringBuilder();
		
		if (this.login(name,password)) {
			//验证登陆成功
			//查询获得项目的集合
			ResultSet projectSet = operatetable.getProjectSetByUserID(operatetable.getUserIDByName(name));
			
			//标志是否有满足时间条件的项目
			boolean flag = false;
			
			while (projectSet.next()) {
				if (inTimeRange(left_time,projectSet.getString(4),projectSet.getString(5),right_time)) {
					//项目时间在时间范围内
					flag = true;
					
					res.append(projectSet.getString(1) + "/");
					res.append(projectSet.getString(3) + "/");
					res.append(projectSet.getString(4) + "/");
					res.append(projectSet.getString(5) + "/");
					res.append(projectSet.getString(6) + "\n");
				}
			}
			if (!flag) {
				return "Search results are empty.\n";
			}
		} else {
			return "Insufficient authority.\n";
		}
		return res.toString();
	}
	
	/**
	 * 通过id删除指定的项目
	 * 验证用户信息
	 * @author zk
	 * @param name
	 * @param password
	 * @param project_id
	 * @throws SQLException 
	 */
	@WebMethod
	public String deleteProjectByID(String name, String password, String project_id) throws Exception {
		
		if (this.login(name,password)){
			if (operatetable.deleteProjectByID(project_id)) {
				return "Delete project success.\n";
			} else {
				return "Edlete project failure.\n";
			}
		} else {
			return "Insufficient authority.\n";
		}
	}
	
	/**
	 * 清除用户名下的所有项目
	 * 验证用户信息
	 * @author zk
	 * @param name
	 * @param password
	 * @throws SQLException 
	 */
	@WebMethod
	public String clearProject(String name, String password) throws Exception {
		
		if (this.login(name,password)){
			if (operatetable.clearAllProject(operatetable.getUserIDByName(name))) {
				return "Clear project success.\n";
			} else {
				return "Clear project failure.\n";
			}
		} else {
			return "Insufficient authority.\n";
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
	@WebMethod(exclude=true)
	public boolean inTimeRange(String left_time,String start_time,String end_time,String right_time) throws java.text.ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//时间范围的左边界和右边界
		Date left = null , right = null;
		//用来接收从结果集中提取的项目开始时间和结束时间
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
	/**
	 * 关闭数据表操作
	 */
	@WebMethod(exclude=true)
	public void closeListService(){
		this.operatetable.close();
	}

}
