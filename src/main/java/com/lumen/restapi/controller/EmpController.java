package com.lumen.restapi.controller;


import com.lumen.restapi.model.Employee;
import com.lumen.restapi.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@Tag(name = "Employee Management", description = "Employee API for CRUD operations")
public class EmpController {

    @Autowired
    public EmpService empService;

   /* @GetMapping("/")
    public String getMessage() {
        return "This is my first rest api";
    }*/

    //localhost:8080/create
   // @Operation(summary = "Create an new employee", description = "Add an new employee to the system")
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp){
        try{
            Employee employee = empService.createEmployee(emp);
            if(employee!=null){
                return new ResponseEntity<>(employee, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/empId/{id}
    //localhost:8080/empId/2
    // @Operation(summary = "Get a emplyee by ID", description = "Fetch an employee based on their ID")
     @GetMapping("/empId/{id}")
     public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee emp = empService.getEmployeeById(id);
        if(emp!=null){
            return ResponseEntity.ok(emp);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }

    //Test
    //step1:- we have created the emp using post method : localhost:8080/create
    //step2:- Get the employee based on empId to check whether emp object is saved or not.

    //step3: localhost:8080/update/1
    //@Operation(summary = "Update an existing employee", description = "Updates an employee by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee emp){
        Employee employee = empService.updateEmployee(id, emp);
        return ResponseEntity.ok(employee);
    }


    //DeleteMapping
    //localhost:8080/delete/2
    //@Operation(summary = "delete an existing employee", description = "delete an employee by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id){
        empService.deleteEmpById(id);
        return ResponseEntity.noContent().build();
    }


}
