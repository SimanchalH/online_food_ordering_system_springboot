package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	
	@Query(value = "SELECT * FROM product WHERE product_first_name = ?1", nativeQuery = true)
	public List<Product> serchUserByName(String product_name);
	
	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM product, state WHERE state_id = product_state", nativeQuery = true)
	public List<Product> serchUserByState(String product_state);
	

}
