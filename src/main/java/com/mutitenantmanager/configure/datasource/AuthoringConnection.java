package com.mutitenantmanager.configure.datasource;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthoringConnection {
	
	@Autowired
	AuthoringDataSourceRouting authoringDataSourceRouting;
	
	public Connection getConnection() {
		try{
			return authoringDataSourceRouting.getConnection();
		} catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
	}
}
