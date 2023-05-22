package com.example.springbootlearning.controller;

import java.util.List;

import com.example.springbootlearning.service.ProfileService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.Database.Entity.Profile;

@RestController
@RequestMapping("/api/v2")
@Hidden
public class ProfileController1 {

    //創建一個計算機程式

    @Autowired
    private ProfileService profileService;

    // 取得資料庫所有資料
    @GetMapping("/profiles")
    public List<Profile> getALL(){
        return profileService.findAll();
    }

    // 新增資料到資料庫
    @PostMapping("/profiles")
    public Profile post(Profile profile){

        // Profile p = new Profile();
        
        // p.setName(name);
        // p.setJob(job);
        // p.setLanguage(language);
        // p.setStatus(status);
        // p.setDescription(description);
        return profileService.save(profile);
    }

    // 取得資料庫一筆資料
    @GetMapping("/profiles/{id}")
    public Profile getOne(@PathVariable long id){
        return profileService.findOne(id);
    }

    // 更新一筆資料
    @PutMapping("/profiles")
    public Profile update(@RequestParam long id,
                          @RequestParam String name,
                          @RequestParam String job,
                          @RequestParam String language,
                          @RequestParam int status,
                          @RequestParam String description) {
        Profile p = new Profile();

        p.setId(id);
        p.setName(name);
        p.setJob(job);
        p.setLanguage(language);
        p.setStatus(status);
        p.setDescription(description);
        return profileService.save(p);
    }

    // 刪除一筆資料
    @DeleteMapping("/profiles/{id}")
    public void deleteOne(@PathVariable long id){
        profileService.delete(id);
    }  

    // 搜尋
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    @PostMapping("/profiles/by")
    // 條件式搜尋
    // public List<Profile> findBy(@RequestParam String description){
    //     return profileServiceImpl.findByDescriptionContains(description);
    // }
    // public List<Profile> findBy(@RequestParam String description){
    //     return profileServiceImpl.findByDescriptionEndsWith(description);
    // }
    // public List<Profile> findBy(@RequestParam String job, @RequestParam int status){
    //     return profileServiceImpl.findByJobAndStatus(job, status);
    // }
    // public List<Profile> findBy(@RequestParam String job){
    //     return profileServiceImpl.findByJob(job);
    // }
    // 自定義搜尋
    // public List<Profile> findBy(@RequestParam int len){
    //     return profileServiceImpl.findByJPQL(len);
    // }
    
    // // 自定義更新
    // public int findBy(@RequestParam int status, @RequestParam long id){
    //     return profileServiceImpl.updateByJPQL(status, id);
    // }
    
    // 自定義刪除
    public int findBy(@RequestParam long id){
        return profileService.deleteByJPQL(id);
    }

}
