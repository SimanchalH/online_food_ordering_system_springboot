package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	
	@Query(value = "SELECT * FROM payment WHERE payment_first_name = ?1", nativeQuery = true)
	public List<Payment> serchUserByName(String payment_name);
	
	@Query(value = "SELECT * FROM payment WHERE payment_email = ?1 AND payment_password = ?2", nativeQuery = true)
	public Payment checkLogin(String login_email, String login_password);
	
}
