package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Enum.SystemParamEnum;
import com.example.springbootlearning.service.ResponseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ResponseService")
public class ResponseServiceImpl implements ResponseService {

//    private long id;
//    private String name;
//    private String username;
//    private int active;
    @Override
    public Map<String, Object> response(long id, String name, String username, int password, int active) {
        Map<String, Object> m = new HashMap<>();
        m.put("Id", id);
        m.put("Name", name);
        m.put("Username", username);
        m.put("Password", password);
        m.put("Active", active);
        return m;
    }
    @Override
    public Map<String, Object> response(String mess) {
        Map<String, Object> m = new HashMap<>();
        m.put("Result", mess);
        return m;
    }
    @Override
    public String getSysParams(SystemParamEnum params) {
        Map<String, String> map = new HashMap<>();
        String message = map.get(params.getMessage());

        return message;
    }

    @Override
    public Map<String, Object> response_Token(String mess) {
        Map<String, Object> m = new HashMap<>();
        m.put("Token: ", mess);
        return m;
    }
}
