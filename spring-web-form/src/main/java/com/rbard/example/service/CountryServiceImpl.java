package com.rbard.example.service;

import com.rbard.example.model.domain.Country;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

  private final List<Country> countries;

  public CountryServiceImpl() {
    this.countries = List.of(
        Country.of(1, "ES", "España"),
        Country.of(2, "MX", "México"),
        Country.of(3, "CL", "Chile"),
        Country.of(4, "AR", "Argentina"),
        Country.of(5, "PE", "Perú"),
        Country.of(6, "CO", "Colombia"),
        Country.of(7, "VE", "Venezuela")
    );
  }

  @Override
  public List<Country> list() {
    return this.countries;
  }

  @Override
  public Country getById(Integer id) {
    return this.countries.stream()
        .filter(country -> country.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("Country not found"));
  }

}
