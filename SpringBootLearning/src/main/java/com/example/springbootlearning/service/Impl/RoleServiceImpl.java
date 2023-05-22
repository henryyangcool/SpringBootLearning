package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Database.Entity.Role;
import com.example.springbootlearning.Database.Repository.RoleRepository;
import com.example.springbootlearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public Role findByUsername(String username) {
        return roleRepository.findByUsername(username);
    }

    @Override
    public Role findByUsernameAndPassword(String username, int password) {
        return roleRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Role findByActiveAndUsername(int active, String username) {
        return roleRepository.findByActiveAndUsername(active,username);
    }

    @Override
    public int updateByUsername(int password, String username) {
        return roleRepository.updateByUsername(password,username);
    }

    @Override
    public void deleteByUsername(String username) {
        roleRepository.deleteByUsername(username);
    }

}
