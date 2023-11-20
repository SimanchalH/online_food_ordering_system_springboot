package com.project.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
