package com.example.springbootlearning.TimeStamp;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

public class TimeEntity {
    @CreatedDate
    @Column(name = "create_time")
//    @Column(name = "create_time", nullable = false)
    public LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
//    @Column(name = "update_time", nullable = false)
    public LocalDateTime updateTime;
}
