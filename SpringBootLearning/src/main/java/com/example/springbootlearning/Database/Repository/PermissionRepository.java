package com.example.springbootlearning.Database.Repository;

//import jakarta.transaction.Transactional;
import com.example.springbootlearning.Database.Entity.Permission;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


@Hidden
//在swagger上隱藏api
public interface PermissionRepository extends JpaRepository<Permission,Long> {
// 透過繼承JpaRepository，可以對Profile實體對應的Database的Table進行CRUD操作

    Permission findByUsername(String username);

    // 自定義更新
    // 修改資料庫內的數據
    // 改權限
    @Modifying
    @Transactional
    // 使用jpql語句
    @Query("update Permission p set p.role=:role where p.username=:username")
    int updateByUsername(String role, String username);

    // 自定義刪除
    // 修改資料庫內的數據
    // 用在需要更動資料庫內的數據，如果發生異常可以倒回原本資料
    // @Modifying & @Transactinoal兩個必須綁在一起
    @Modifying
    @Transactional
    // 使用jpql語句
    @Query("delete from Permission p where p.username=?1")
    void deleteByUsername(String username);

    @Query("select p from Permission p where p.username=:username")
    int findUsername(String username);

//    @Query("select p from Permission p where p.username=:username and p.password=:password")
//    boolean findUsernameAndPassword(String username, int password);
}
