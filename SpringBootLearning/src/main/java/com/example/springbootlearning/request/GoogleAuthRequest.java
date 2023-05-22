package com.example.springbootlearning.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Hidden
public class GoogleAuthRequest {
    private int code;
    private String username;
}
