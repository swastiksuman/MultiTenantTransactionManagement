package com.mutitenantmanager.configure.datasource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext ctx;

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
		
	}
	
	

}
