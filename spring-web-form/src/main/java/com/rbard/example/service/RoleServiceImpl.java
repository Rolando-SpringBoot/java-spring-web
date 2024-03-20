package com.rbard.example.service;

import com.rbard.example.model.domain.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final List<Role> roles;

  public RoleServiceImpl() {
    this.roles = List.of(
        Role.of(1, "Administrator", "ROLE_ADMIN"),
        Role.of(2, "User", "ROLE_USER"),
        Role.of(3, "Moderator", "ROLE_MODERATOR")
    );
  }

  @Override
  public List<Role> list() {
    return this.roles;
  }

  @Override
  public Role findById(Integer id) {
    return this.roles.stream()
        .filter(role -> role.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("role not found"));
  }

}
