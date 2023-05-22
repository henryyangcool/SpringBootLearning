package com.example.springbootlearning.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Hidden
public class UpdatePerRequest {
    private String username;
    private int password;
    private String target;
    private String permission;
}
