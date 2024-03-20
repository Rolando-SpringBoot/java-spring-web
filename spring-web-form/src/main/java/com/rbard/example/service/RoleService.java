package com.rbard.example.service;

import com.rbard.example.model.domain.Role;
import java.util.List;

public interface RoleService {

  List<Role> list();
  Role findById(Integer id);

}
