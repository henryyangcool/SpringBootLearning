package com.example.springbootlearning.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Hidden
public class UpdateUserRequest {
    private String username;
    private int password;
    private int newPassword;
}
