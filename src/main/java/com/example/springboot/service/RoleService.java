package com.example.springboot.service;

import com.example.springboot.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    void updateRole(Role role);
    void removeRoleById(Long id);
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}
