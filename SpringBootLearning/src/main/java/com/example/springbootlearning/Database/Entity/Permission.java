package com.example.springbootlearning.Database.Entity;

import com.example.springbootlearning.TimeStamp.TimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// @ConfigurationProperties(prefix = "book")
// // 這段註解會去抓config/application.yml裡面的參數
// @Component
// // 跟@Autowired一組

@Entity
@Getter
@Setter
@Table(name = "permission")
//宣告這個class為資料Model(資料庫)
public class Permission extends TimeEntity {

    // 連線到資料庫 自動生成資料庫的table
    // 依據變數 建立欄位

    // 每當有一筆新資料 就會依據策略自動(Auto)生成主鍵
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String role;
}
