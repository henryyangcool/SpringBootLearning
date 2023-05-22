package com.example.springbootlearning.Database.Entity;

import com.example.springbootlearning.TimeStamp.TimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.Entity;
//import org.springframework.data.annotation.Id;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;


// @ConfigurationProperties(prefix = "book")
// // 這段註解會去抓config/application.yml裡面的參數
// @Component
// // 跟@Autowired一組

@Entity
@Getter
@Setter
//@Table(name = "profile")
//宣告這個class為資料Model(資料庫)
public class Profile extends TimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // 連線到資料庫 自動生成資料庫的table 
    // 依據變數 建立欄位
    // 每當有一筆新資料 就會依據策略自動(Auto)生成主鍵
    private long id;
    private String name;
    private String job;
    private String language;
    private int status;
    private String description;
    public Profile() {}

}
