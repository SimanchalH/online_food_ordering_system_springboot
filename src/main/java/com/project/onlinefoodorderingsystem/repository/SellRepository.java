package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Sell;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {
	
	
	@Query(value = "SELECT * FROM sell WHERE sell_code = ?1", nativeQuery = true)
	public List<Sell> serchUserByName(String sell_name);
	
	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM sell, state WHERE state_id = sell_state", nativeQuery = true)
	public List<Sell> serchUserByState(String sell_state);
	

}
