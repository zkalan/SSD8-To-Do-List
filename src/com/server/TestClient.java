package com.server;

public class TestClient {

	public static void main(String[] args) {
		//初始化对象
		ListService listservice = new ListService();
		
		System.out.println(listservice.getHello("zkalan"));

	}

}
