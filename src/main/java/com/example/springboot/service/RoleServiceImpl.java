package com.example.springboot.service;

import com.example.springboot.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements com.example.springboot.service.RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleRepository (RoleDao roleRepository) {
        this.roleDao = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public void removeRoleById(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
