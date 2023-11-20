package com.project.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Saluation;

@Repository
public interface SaluationRepository extends JpaRepository<Saluation, Long> {

}
