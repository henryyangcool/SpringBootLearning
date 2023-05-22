package com.example.springbootlearning.Database.Repository;

import java.util.List;

import com.example.springbootlearning.Database.Entity.Profile;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Hidden
//在swagger上隱藏api
public interface ProfileRepository extends JpaRepository<Profile,Long> {
// 透過繼承JpaRepository，可以對Profile實體對應的Database的Table進行CRUD操作
    
    List<Profile> findByJob(String job);
    
    // JPA獨有的多條件式搜尋語法 
    List<Profile> findByJobAndStatus(String job, int status);

    List<Profile> findByDescriptionEndsWith(String description);

    List<Profile> findByDescriptionContains(String description);


    // 自定義搜尋
    // 使用jpql語句 
    @Query("select p from Profile p where length(p.name) > ?1")
    // 使用sql語句
    // @Query(value = "select * from profile where length(name) > ?1", nativeQuery = true)
    List<Profile> findJPQL(int len);

    // 自定義更新
    // 修改資料庫內的數據
    @Modifying
    // 使用jpql語句 
    @Query("update Profile p set p.status=?1 where p.id=?2")
    int updateByJPQL(int status, long id);

    // 自定義刪除
    // 修改資料庫內的數據
    @Modifying
    // 使用jpql語句 
    @Query("delete from Profile p where p.id=?1")
    int deleteByJPQL(long id);
}
