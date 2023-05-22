package com.example.springbootlearning.service;

import com.example.springbootlearning.Enum.SystemParamEnum;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service("ResponseService")
public interface ResponseService {

    Map<String, Object> response(String mess);
    Map<String, Object> response(long id, String name, String username, int password, int active);
//    Map<String, Object> response(SystemParamEnum systemParamEnum);
    Map<String, Object> response_Token(String mess);
    String getSysParams(SystemParamEnum params);
}
