/*
 * http://howtodoinjava.com/2013/12/08/spring-3-2-5-release-and-hibernate-4-integration-example-tutorial/
 * 
 */

package com.howtodoinjava.dao;

import java.util.List;
import com.howtodoinjava.entity.EmployeeEntity;

public interface EmployeeDAO 
{
    public void addEmployee(EmployeeEntity employee);
    public List<EmployeeEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
}