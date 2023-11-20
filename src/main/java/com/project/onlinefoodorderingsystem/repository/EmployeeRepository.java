package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.onlinefoodorderingsystem.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	@Query(value = "SELECT * FROM employee WHERE employee_first_name = ?1", nativeQuery = true)
	public List<Employee> serchUserByName(String employee_name);
	

	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM employee, state WHERE state_id = employee_state", nativeQuery = true)
	public List<Employee> serchUserByState(String employee_state);
	

}
