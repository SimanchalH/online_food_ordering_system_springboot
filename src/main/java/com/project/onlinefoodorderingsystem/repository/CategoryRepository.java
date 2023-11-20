package com.project.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	
	@Query(value = "SELECT * FROM category WHERE category_first_name = ?1", nativeQuery = true)
	public List<Category> serchUserByName(String category_name);
	
	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM category, state WHERE state_id = category_state", nativeQuery = true)
	public List<Category> serchUserByState(String category_state);
	

}
