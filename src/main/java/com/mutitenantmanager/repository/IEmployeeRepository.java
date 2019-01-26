package com.mutitenantmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.mutitenantmanager.entity.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Long> {

}
