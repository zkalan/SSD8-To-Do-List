package com.server;

import java.sql.SQLException;
import java.text.ParseException;

public class TestClient {

	public static void main(String[] args) throws SQLException, ParseException {
		//初始化对象
		ListService listservice = new ListService();
		
//		System.out.println(listservice.register("root", "root"));
//		System.out.println(listservice.register("admin", "admin"));
//		System.out.println(listservice.register("user3", "user3"));
//		System.out.println(listservice.register("user4", "user4"));
//		System.out.println(listservice.register("user5", "user5"));
//		System.out.println(listservice.register("user6", "user6"));
//		System.out.println(listservice.register("user7", "user7"));
//		System.out.println(listservice.register("user8", "user8"));
//		System.out.println(listservice.addProject("root", "root", "the second project", "2016-12-30-10:0:0", "2016-12-30-11:0:0", "So easy!"));
//		
//		System.out.println(listservice.addProject("root", "root", "the third project", "2017-12-30-10:0:0", "2017-12-30-11:0:0", "So easy!"));
		
//		System.out.println(listservice.addProject("admin", "admin", "my first", "2015-12-30-10:0:0", "2015-12-30-11:0:0", "So easy!"));
//		System.out.println(listservice.showProject("root", "root"));
		
		System.out.println(listservice.searchProjectByTime("root", "root", "2016-12-1 0:0:0", "2016-12-31 0:0:0"));
		
		System.out.println(listservice.searchProjectByTime("admin", "admin", "2015-12-1 0:0:0", "2015-12-31 0:0:0"));
		
		System.out.println(listservice.searchProjectByTime("admin", "admi", "2015-12-1 0:0:0", "2015-12-31 0:0:0"));
		
//		System.out.println(listservice.showProject("root", "root"));
		
//		System.out.println(listservice.deleteProjectByID("root", "root", "4"));
		
		System.out.println(listservice.clearProject("root", "root"));
		
		System.out.println(listservice.showProject("root", "root"));
		
		listservice.closeListService();

	}

}
