package com.example.springbootlearning.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
//    @NotBlank
    private String username;

//    @NotBlank
    private int password;
}
