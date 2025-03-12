package com.lumen.restapi.service;

import com.lumen.restapi.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpService {

     private final List<Employee> empList = new ArrayList<Employee>();

    //add employee to empList
    public Employee createEmployee(Employee emp) {
        empList.add(emp);
        return emp;
    }

    //get employee object based on the employee id
    public Employee getEmployeeById(Long id) {  //2===2
        return empList.stream().filter(emp -> emp.id==id).findFirst().orElse(null);
    }

    //update employee details based on employee id
    public Employee updateEmployee(Long id, Employee requestEmp) {
        Employee employee = getEmployeeById(id);
        if(employee!=null){
            employee.setName(requestEmp.getName());
            employee.setEmail(requestEmp.getEmail());
            employee.setDept(requestEmp.getDept());
        }
        return requestEmp;
    }

    //Delete the employee based on the empId
    public void deleteEmpById(Long id) {
        empList.removeIf(emp -> emp.getId() == id);
    }
}
