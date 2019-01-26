package com.mutitenantmanager.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutitenantmanager.MultiTenantTransactionManagementApplication;
import com.mutitenantmanager.configure.datasource.ApplicationContextUtil;
import com.mutitenantmanager.configure.datasource.TenantContext;
import com.mutitenantmanager.configure.thread.DeleteThread;
import com.mutitenantmanager.configure.thread.SaveThread;
import com.mutitenantmanager.entity.Employee;
import com.mutitenantmanager.repository.IEmployeeRepository;

@RestController
public class TestController {
	
	@Autowired
	IEmployeeRepository employeeRepository;
	
	@GetMapping("/")
	public Employee getAllThings(){
		System.out.println("THREAD ID:" +Thread.currentThread().getId());
		return employeeRepository.findById(2L).get();
	}
	
	@GetMapping("/test")
	public Employee getAnotherThings(){
		try{
			
			Thread.sleep(10000);
		}catch(Exception e){
			
		}
		TenantContext.setCurrentTenant(1L);
		Employee e =  employeeRepository.findById(2L).get();
		TenantContext.remove();
		return e;
	}
	
	@PostMapping("/test2")
	public void save(){
		ApplicationContext ctx = ApplicationContextUtil.getApplicationContext();
    	TenantContext.setCurrentTenant(2L);
    	SaveThread s1 = (SaveThread) ctx.getBean(SaveThread.class);
    	s1.setEnv(2L);
    	s1.start();
    	/*try {
			s1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	SaveThread s2 = (SaveThread) ctx.getBean(SaveThread.class);
    	s2.setEnv(1L);
    	s2.start();
	}
	
	@PostMapping("/delete")
	public void delete(){
		DeleteThread deleteThread = ApplicationContextUtil.getApplicationContext().getBean(DeleteThread.class);
		deleteThread.setEnv(2L);
		deleteThread.start();
		try {
			deleteThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DeleteThread deleteThread2 = ApplicationContextUtil.getApplicationContext().getBean(DeleteThread.class);
		deleteThread2.setEnv(1L);
		deleteThread2.start();
	}
	
	@PostMapping("/createBoth")
	public void createBoth(){
		Employee e1 = new Employee();
		e1.setEmployeeId(4L);
		e1.setEmployeeName("Rahul");
		
		Employee e2 = new Employee();
		e2.setEmployeeId(5L);
		e2.setEmployeeName("Dravid");
		
		TenantContext.setCurrentTenant(2L);
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		TenantContext.remove();
		TenantContext.setCurrentTenant(1L);
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		TenantContext.remove();
	}
}
