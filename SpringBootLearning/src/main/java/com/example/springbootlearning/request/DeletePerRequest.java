package com.example.springbootlearning.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Hidden
public class DeletePerRequest {
    private String username;
    private int password;
    private String target;
}
