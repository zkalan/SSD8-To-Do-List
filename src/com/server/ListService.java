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
	
	@WebMethod
	public String getHello(String name){
		
		operateTable op = new operateTable();
		
		return name + op.hhhh();
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
	public String addProject(String name, String password, String project_title, String start_time, String end_time,
			String summary) {
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

}
