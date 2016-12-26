package com.server;

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
	

	@Override
	public String register(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String showProject(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addProject(String name, String password, String summary, String start_time, String end_time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchProjectByTime(String name, String password, String left_time, String right_time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProjectByID(String name, String password, String project_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String clearProject(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 关闭数据表操作
	 */
	@WebMethod(exclude=true)
	public void closeListService(){
		this.operatetable.close();
	}

}
