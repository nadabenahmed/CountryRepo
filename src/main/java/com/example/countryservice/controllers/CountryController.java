package com.example.countryservice.controllers;

import com.example.countryservice.beans.Country;
import com.example.countryservice.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country-controller") // pour que Swagger affiche country-controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    // ✅ GET ALL
    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // ✅ GET BY ID
    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id) {
        Country country = countryService.getCountryById(id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    // ✅ GET BY NAME
    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam String name) {
        Country country = countryService.getCountryByName(name);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    // ✅ ADD
    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        Country addedCountry = countryService.addCountry(country);
        return new ResponseEntity<>(addedCountry, HttpStatus.CREATED);
    }

    // ✅ UPDATE
    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id, @RequestBody Country country) {
        Country updatedCountry = countryService.updateCountry(id, country);
        return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
    }

    // ✅ DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
