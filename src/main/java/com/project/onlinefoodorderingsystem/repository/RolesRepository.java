package com.project.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
