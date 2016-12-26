package com.server;

import javax.xml.ws.Endpoint;

/**
 * 代为发布服务的入口
 * @author zk
 *
 */
public class ListServer {

	public static void main(String[] args) {
		
		Endpoint.publish("http://127.0.0.1:80/ListService/ServiceHello", new ListService());
		System.out.println("Service success!");
	}

}
