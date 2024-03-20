package com.rbard.example.editor;

import com.rbard.example.model.domain.Country;
import com.rbard.example.service.CountryService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

  @Autowired
  private CountryService countryService;

  @Override
  public void setAsText(String idString) throws IllegalArgumentException {
    try {
      Integer id = Integer.valueOf(idString);
      Country country = this.countryService.getById(id);
      this.setValue(country);
    } catch (NumberFormatException ex) {
      this.setValue(null);
    }
  }

}
