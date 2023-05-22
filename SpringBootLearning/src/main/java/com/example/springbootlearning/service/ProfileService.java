package com.example.springbootlearning.service;

import java.util.List;

import jakarta.transaction.Transactional;

import com.example.springbootlearning.Database.Entity.Profile;
//import org.springframework.transaction.annotation.Transactional;

//import jakarta.transaction.Transactional;

public interface ProfileService {

    // 讓ProfileService.java可以使用ProfileRepository而不用實例化
//    @Autowired
//    ProfileRepository profileRepository = null;

    // 回傳資料庫內所有資料
    public List<Profile> findAll();

    // 新增一筆個人資料
    public Profile save(Profile p);

    // 取得特定id資料
    public Profile findOne(long id);

    // 刪除特定id資料
    public void delete(long id);

    // 條件式搜尋
    public List<Profile> findByJob(String job);

    public List<Profile> findByJobAndStatus(String job, int status);

    public List<Profile> findByDescriptionEndsWith(String description);

    public List<Profile> findByDescriptionContains(String description);

    // 自定義搜尋
    public List<Profile> findByJPQL(int len);

    // 用在需要更動資料庫內的數據，如果發生異常可以倒回原本資料
    @Transactional
    // 自定義更新
    public int updateByJPQL(int status, long id);

    @Transactional 
    // 自定義刪除
    public int deleteByJPQL(long id);
}
