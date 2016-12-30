package com.server;

import java.text.ParseException;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 定义接口
 * @author zk
 *
 */
@WebService
public interface ListServiceInterface {
	
	@WebMethod
	//用户注册（检测是否存在）
	//成功：Register success 注册成功！
	//失败：Register failure注册失败！
	//用户名已存在：Username alrearly exists 用户名已存在，请重试...
	public String register(String name,String password) throws Exception;

	//登陆成功返回为真
	//登陆失败返回为假
	@WebMethod
	public boolean login(String name,String password) throws Exception;

	// 序号 | 开始时间 | 结束时间 |
//	 		|          |          |
	// 概述：######################
	//#############################
	//################
	@WebMethod
	public String showProject(String name,String password) throws Exception;



	//添加项目（开始时间、结束时间、描述）
	//失败：Add project failure 添加项目失败！
	//已存在：Project alrearly exists 同名项目已存在，请重试...
	//成功：Add project success 添加项目成功！
	@WebMethod
	public String addProject(String name,String password,String title,String start_time,String end_time,String summary) throws Exception;

	//查询项目（指定时间段内的项目，列出开始时间、结束时间、标签）
	//查询结果为空...
	//满足条件的项目有：
	// 序号 | 开始时间 | 结束时间 |
	//	 		|          |          |
	// 概述：######################
	//#############################
	//################
	@WebMethod
	public String searchProjectByTime(String name,String password,String left_time,String right_time) throws Exception, ParseException;

	//删除项目（通过项目ID）
	//删除成功！
	//删除失败！
	//项目不存在，请重试...
	@WebMethod
	public String deleteProjectByID(String name,String password,String project_id) throws Exception;

	//清空项目（删除所有项目）
	//清除成功！
	//清除失败！
	@WebMethod
	public String clearProject(String name,String password) throws Exception;



}
