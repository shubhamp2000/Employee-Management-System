package com.itv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.entity.Employee;
import com.itv.exception.ResourceNotFoundException;
import com.itv.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepo employeeRepo;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee) ;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		//		Optional<Employee> employee = employeeRepo.findById(id);
		//		if (employee.isPresent()) {
		//			return employee.get();					
		//		}else {
		//			throw new ResourceNotFoundException("Employee","Id",id);
		//		

		return employeeRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee","Id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check weather employee with given id is exist in database or not
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException ("Employee","Id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing emp to database
		employeeRepo.save(existingEmployee);
		return existingEmployee ;
	}

	@Override
	public void deleteEmployee(long id) {

		// check weather the emp exist in database or not 	
		employeeRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException ("Employee","Id",id));
		employeeRepo.deleteById(id);
	}

}
