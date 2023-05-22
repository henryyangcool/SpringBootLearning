
package com.example.springbootlearning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



// @Controller
@RestController
// 會回傳JSON格式
@RequestMapping("/api/v1")
//@Table(name = "profile")
@Hidden
//在swagger上隱藏api
public class HelloController{

    // // 引入config/application.yaml的檔案資訊
    // @Value("${profile.name}")
    // private String name;
    // @Value("${profile.job}")
    // private String job;
    // @Value("${profile.language}")
    // private String language;
    // @Value("${profile.description}")
    // private String description;
    
    // @Autowired
    // // 跟@Componet是一起
    // private Profile profile ;
    
    // @RequestMapping("/say")
    // @RequestMapping(value = "/say", method = RequestMapping.GET)
    @GetMapping("/say")
    public String Hello(){
        return "profiles";
    }

    @GetMapping("/profile")
    // @ResponseBody
    // 新增@ResponseBody會把下面的函式直接輸出
    public Object getAll(@RequestParam("Height") int height, @RequestParam(value = "weight",defaultValue="65" ) int weight){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Henry");
        map.put("age", "21");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "Chloe");
        map2.put("age", "19");

        List<Map> contents = new ArrayList<>();
        contents.add(map);
        contents.add(map2);

        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("Height", height);
        pagemap.put("Weight", weight);
        pagemap.put("contents",contents);

        return pagemap;
    }
    @PostMapping("/profile")
    public Object post(@RequestParam("name") String name, 
                       @RequestParam("job") String job, 
                       @RequestParam("language") String language){

        Map<String, Object> profile = new HashMap<>();
        profile.put("name", name);
        profile.put("job",job);
        profile.put("language", language);

        return profile;
    }

    // [a-z_]+ 正則表達式 規定只能輸入小寫和底線
    @GetMapping("/profile/{id}")
    // 處理輸入參數需要加@PathVariablew
    public Object getOne(@PathVariable long id){
    // public Object getOne(@PathVariable("id") long bid){
    //    return profile;
    // 流程
    // 1. 在application.yaml定義出共同使用的變數
    // 2. 在Book.java使用@ConfigurationProperties(prefix = "book")來存取application.yaml變數
    // 3. 在HelloController中import Book.java 使用@Autowired進行綁定

        return null;
    }

}
