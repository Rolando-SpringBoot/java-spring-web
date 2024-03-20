package com.rbard.example.service;

import com.rbard.example.model.domain.Country;
import java.util.List;

public interface CountryService {

  List<Country> list();
  Country getById(Integer id);

}
