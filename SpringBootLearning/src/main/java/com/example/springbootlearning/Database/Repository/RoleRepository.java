package com.example.springbootlearning.Database.Repository;


import com.example.springbootlearning.Database.Entity.Role;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Hidden
//在swagger上隱藏api
public interface RoleRepository extends JpaRepository<Role,Long> {
// 透過繼承JpaRepository，可以對Profile實體對應的Database的Table進行CRUD操作

    Role findByUsername(String username);
    Role findByActiveAndUsername(int active, String username);
    Role findByUsernameAndPassword(String username, int password);

    // 自定義更新
    // 修改資料庫內的數據
    // 改密碼
    @Modifying
    @Transactional
    // 使用jpql語句 
    @Query("update Role r set r.password=:password where r.username=:username")
    int updateByUsername(int password, String username);

    // 自定義刪除
    // 修改資料庫內的數據
    // 用在需要更動資料庫內的數據，如果發生異常可以倒回原本資料
    // @Modifying & @Transactinoal兩個必須綁在一起
    @Modifying
    @Transactional
    // 使用jpql語句
    @Query("delete from Role r where r.username=?1")
    void deleteByUsername(String username);

    @Query("select r from Role r where r.username=:username")
    int findUsername(String username);

//    @Query("select r from Role r where r.username=:username and r.password=:password")
//    boolean findUsernameAndPassword(String username, int password);
}
