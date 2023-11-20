package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	
	
	@Query(value = "SELECT * FROM company WHERE company_first_name = ?1", nativeQuery = true)
	public List<Company> serchUserByName(String company_name);
	
	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM company, state WHERE state_id = company_state", nativeQuery = true)
	public List<Company> serchUserByState(String company_state);
	

}
