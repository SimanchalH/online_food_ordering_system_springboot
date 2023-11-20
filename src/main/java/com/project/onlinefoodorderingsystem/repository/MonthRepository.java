package com.project.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Month;

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {

}
