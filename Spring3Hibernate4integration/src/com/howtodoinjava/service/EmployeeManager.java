/*
 * http://howtodoinjava.com/2013/12/08/spring-3-2-5-release-and-hibernate-4-integration-example-tutorial/
 * 
 */

package com.howtodoinjava.service;

import java.util.List;

import com.howtodoinjava.entity.EmployeeEntity;

public interface EmployeeManager {
	public void addEmployee(EmployeeEntity employee);
    public List<EmployeeEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
}
