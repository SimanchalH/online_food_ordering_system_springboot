package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	
	@Query(value = "SELECT * FROM customer WHERE customer_first_name = ?1", nativeQuery = true)
	public List<Customer> serchUserByName(String customer_name);
	
	@Query(value = "SELECT * FROM customer WHERE customer_email = ?1 AND customer_password = ?2", nativeQuery = true)
	public Customer checkLogin(String login_email, String login_password);
	
	@Query(value = "SELECT * FROM customer WHERE customer_email = ?1", nativeQuery = true)
	public List<Customer> getUserExits(String email);
}
