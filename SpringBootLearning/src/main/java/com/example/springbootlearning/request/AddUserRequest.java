package com.example.springbootlearning.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Hidden
public class AddUserRequest {
    private String name;
    private String username;
    private int password;
    private int active;
    private String permission;
}
