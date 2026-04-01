package com.example.countryservice.services;

import com.example.countryservice.beans.Country;
import com.example.countryservice.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRep;

    // GET ALL
    public List<Country> getAllCountries() {
        return countryRep.findAll();
    }

    // GET BY ID
    public Country getCountryById(Integer id) {
        return countryRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id " + id));
    }

    // GET BY NAME
    public Country getCountryByName(String name) {
        return countryRep.findByName(name)
                .orElseThrow(() -> new RuntimeException("Country not found with name " + name));
    }

    // ADD
    public Country addCountry(Country country) {
        return countryRep.save(country);
    }

    // UPDATE
    public Country updateCountry(Integer id, Country country) {
        Country existingCountry = countryRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id " + id));

        existingCountry.setName(country.getName());
        existingCountry.setCapital(country.getCapital());

        return countryRep.save(existingCountry);
    }

    // DELETE
    public void deleteCountry(Integer id) {
        Country existingCountry = countryRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id " + id));

        countryRep.delete(existingCountry);
    }
}
