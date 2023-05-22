package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Database.Entity.Permission;
import com.example.springbootlearning.Database.Repository.PermissionRepository;
import com.example.springbootlearning.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission p) {
        return permissionRepository.save(p);
    }

    @Override
    public Permission findByUsername(String username) {
        return permissionRepository.findByUsername(username);
    }

    @Override
    public int updateByUsername(String role, String username) {
        return permissionRepository.updateByUsername(role, username);
    }

    @Override
    public void deleteByUsername(String username) {
        permissionRepository.deleteByUsername(username);
    }

//    @Override
//    public boolean findUsernameAndPassword(String username, int password) {
//        return permissionRepository.findUsernameAndPassword(username, password);
//    }
}
