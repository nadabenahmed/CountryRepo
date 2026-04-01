package com.example.countryservice.repositories;

import com.example.countryservice.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findByName(String name); // méthode pour GET BY NAME
}
