package com.project.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.onlinefoodorderingsystem.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
