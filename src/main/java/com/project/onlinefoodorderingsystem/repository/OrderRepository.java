package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
	@Query(value = "SELECT * FROM order WHERE order_code = ?1", nativeQuery = true)
	public List<Order> serchUserByName(String order_name);
	
	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM order, state WHERE state_id = order_state", nativeQuery = true)
	public List<Order> serchUserByState(String order_state);
	

}
