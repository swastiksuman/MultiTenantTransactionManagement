package com.mutitenantmanager.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutitenantmanager.configure.datasource.TenantContext;
import com.mutitenantmanager.entity.Employee;
import com.mutitenantmanager.repository.IEmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	IEmployeeRepository employeeRepository;
	
	public Employee getEmployee(){
		return employeeRepository.findById(1L).get();
	}
	
	@Transactional
	public Employee createEmployee(boolean isBlank){
		Employee e1 = new Employee();
		e1.setEmployeeId(4L);
		e1.setEmployeeName("Rahul");
		
		Employee e2 = new Employee();
		e2.setEmployeeId(5L);
		if(isBlank)
			e2.setEmployeeName(null);
		else
			e2.setEmployeeName("Dravid");
		employeeRepository.save(e1);
		return employeeRepository.save(e2);
		
	}
	
	public void deleteEmpployee(){
		employeeRepository.deleteById(2L);
	}
}
