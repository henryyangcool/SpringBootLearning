package com.example.springbootlearning.service;

import com.example.springbootlearning.Database.Entity.Role;

//@Service
public interface RoleService {

    // 新增一筆個人資料
    public Role save(Role r);

    public Role findByUsername(String username);

    public Role findByUsernameAndPassword(String username, int password);

    public Role findByActiveAndUsername(int active, String username);

    // 自定義更新
    public int updateByUsername(int password, String username);

    // 自定義刪除
    public void deleteByUsername(String username);



//    public boolean findUsernameAndPassword(String username, int password);
}
