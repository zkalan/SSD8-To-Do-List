package com.server;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class ListServer {
	
	public String getHello(String name){
		return "Œ“Ω–" + name;
	}

	public static void main(String[] args) {
		
		Endpoint.publish("http://127.0.0.1:80/ListService/ServiceHello", new ListServer());
		System.out.println("Service success!");
	}

}
