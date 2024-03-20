package com.rbard.example.editor;

import com.rbard.example.model.domain.Role;
import com.rbard.example.service.RoleService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolePropertyEditor extends PropertyEditorSupport {

  @Autowired
  private RoleService roleService;

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    try {
      Integer id = Integer.valueOf(text);
      Role role = this.roleService.findById(id);
      this.setValue(role);
    } catch (NumberFormatException ex) {
      this.setValue(null);
    }
  }
}
