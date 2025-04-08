package com.itv.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itv.entity.Employee;
import com.itv.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController { 

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// build Create employee RestAPI
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee> (employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	//Built get all Employee rest api
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}

	// build get employee by id REST API
	//http://localhost:8080/api/employees/1 
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
	}

	// build update employee REST API
	//http://localhost:8080/api/employees/1 (id of the emp which we want to upd)

	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(
			@PathVariable("id") long id,
			@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	// build delete employee REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
	}

}
