package com.mutitenantmanager.configure.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mutitenantmanager.configure.datasource.TenantContext;
import com.mutitenantmanager.service.EmployeeService;

@Component
@Scope("prototype")
public class DeleteThread extends Thread{
	
	Long env;
	
	@Autowired
	EmployeeService employeeService;
	
	public void run() {
		TenantContext.setCurrentTenant(env);
		employeeService.deleteEmpployee();
	}

	public Long getEnv() {
		return env;
	}

	public void setEnv(Long env) {
		this.env = env;
	}
}
