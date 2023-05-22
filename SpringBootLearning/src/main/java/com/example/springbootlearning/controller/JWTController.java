package com.example.springbootlearning.controller;

import com.example.springbootlearning.request.AuthRequest;
import com.example.springbootlearning.service.JWTService;
import com.example.springbootlearning.service.ResponseService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Token")
// @Controller
@RestController
// 會回傳JSON格式
@RequestMapping("/api/token")
//@SecurityRequirement(name = "bearerAuth")
public class JWTController {

    @Autowired
    private JWTService jwtTokenService;
    @Autowired
    private ResponseService responseService;

//        變數帶在body
    @Hidden
    @PostMapping("/login")
    @ApiResponse(responseCode = "401", description = "沒有權限")
    public ResponseEntity<Map<String, Object>> issueToken(@Valid @RequestBody AuthRequest authRequest) {
        String token = jwtTokenService.generateToken(authRequest);
//        Collections.singleton(key, value) 只可讀不可寫
        if(!token.equals("No Permission")){
            return ResponseEntity.ok(responseService.response(token));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(token));
        }
    }

//    @PostMapping("/parse")
//    public ResponseEntity<Map<String, Object>> parseToken(@RequestBody Map<String, String> request) {
//        String token = request.get("token");
//        Map<String, Object> response = jwtTokenService.parseToken(token);
//
//        return ResponseEntity.ok(response);
//    }

//    變數帶在Header
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestParam String username,
//                                @RequestParam int password) {
//        JWTTokenService jwtTokenService = new JWTTokenService();
//        String token = jwtTokenService.generateToken(username, password);
//
//        return ResponseEntity.status(HttpStatus.OK).body(token);
//    }

//    @PostMapping("/parse")
//    public String parseToken(@RequestBody String token) {
//        JWTTokenService jwtTokenService = new JWTTokenService();
//
//        if(jwtTokenService.validateToken(token)){
//            return "ok";
//        }else {
//            return "not ok";
//        }
//    }
}
