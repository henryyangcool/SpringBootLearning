package com.example.springbootlearning.service;

import com.example.springbootlearning.Database.Entity.Permission;


//@Service
public interface PermissionService {

    // 新增一筆個人資料
    public Permission save(Permission p);

    public Permission findByUsername(String username);

    public int updateByUsername(String role, String username);
    // 自定義刪除
    public void deleteByUsername(String username);



//    public boolean findUsernameAndPassword(String username, int password);
}
