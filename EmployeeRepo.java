package com.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
 