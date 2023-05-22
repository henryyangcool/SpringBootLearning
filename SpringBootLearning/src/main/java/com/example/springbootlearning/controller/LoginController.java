package com.example.springbootlearning.controller;

import com.example.springbootlearning.Database.Entity.Role;
import com.example.springbootlearning.Database.Entity.Permission;
import com.example.springbootlearning.Enum.SystemParamEnum;
import com.example.springbootlearning.Log.JLogger;
import com.example.springbootlearning.request.*;
import com.example.springbootlearning.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "使用者操作")
@RestController
//回傳json格式
@RequestMapping("/api/login")
//@SecurityRequirement(name = "bearerAuth")
public class LoginController {
    String mess;

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private ApiandIPService requestService;;

    @Operation(summary = "增加使用者資料")
    @PostMapping("/adduser")
    public ResponseEntity<Map<String, Object>> post(@Valid @RequestBody AddUserRequest ad,
                                                    HttpServletRequest request) {

        String name = ad.getName();
        String username = ad.getUsername();
        int password = ad.getPassword();
        int active = ad.getActive();
        String permission = ad.getPermission();

//        JLogger.info();

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        }
        else if (roleService.findByUsername(username) != null) {
            mess = responseService.getSysParams(SystemParamEnum.USERREPEAT);
            JLogger.error(mess);

            return ResponseEntity.badRequest().body(responseService.response(mess));
        } else {
            Permission per = new Permission();
            per.setUsername(username);
            per.setRole(permission);
            permissionService.save(per);

            Role r = new Role();
            r.setName(name);
            r.setUsername(username);
            r.setPassword(password);
            r.setActive(active);
            roleService.save(r);

            mess = "新增成功 id = " + r.getId();

            return ResponseEntity.ok().body(responseService.response(mess));
        }
    }

//    更新user
    @Operation(summary = "更新使用者密碼")
    @PostMapping("/updateUser")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody UpdateUserRequest UpUs,
                                                      HttpServletRequest request) {

        String username = UpUs.getUsername();
        int password = UpUs.getPassword();
        int newPassword = UpUs.getNewPassword();

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if(roleService.findByUsernameAndPassword(username, password) == null){
            mess = "使用者帳號或密碼錯誤 無法更改";
        } else if (roleService.findByActiveAndUsername(0,username) != null) {
            mess = "使用者帳號未啟用 無法更改";
        } else {
            roleService.updateByUsername(newPassword, username);
            mess = responseService.getSysParams(SystemParamEnum.SUCCESS);
            return ResponseEntity.ok().body(responseService.response(mess));
        }
        JLogger.error(mess);
        return ResponseEntity.badRequest().body(responseService.response(mess));
    }

//    刪除user
    @Operation(summary = "刪除使用者資料")
    @PostMapping("/deleteUser")
    public ResponseEntity<Map<String, Object>> delete(@Valid @RequestBody AuthRequest authRequest,
                                                      HttpServletRequest request) {
        String username = authRequest.getUsername();
        int password = authRequest.getPassword();

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if(roleService.findByUsernameAndPassword(username, password) == null){
            mess = responseService.getSysParams(SystemParamEnum.ERROR);
            JLogger.error(mess);
            return ResponseEntity.badRequest().body(responseService.response(mess));
        } else {
            roleService.deleteByUsername(username);
            permissionService.deleteByUsername(username);
            mess = responseService.getSysParams(SystemParamEnum.SUCCESS);
            return ResponseEntity.ok().body(responseService.response(mess));
        }
    }

    //    搜尋user
    @Operation(summary = "檢視使用者資料")
    @ApiResponse(responseCode = "400", description = "參數輸入錯誤")
    @ApiResponse(responseCode = "401", description = "沒有權限")
    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@Valid @RequestBody AuthRequest authRequest,
                                                      HttpServletRequest request){
        String username = authRequest.getUsername();
        int password = authRequest.getPassword();
        Role r = roleService.findByUsernameAndPassword(username, password);

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if (r == null) {
            mess = responseService.getSysParams(SystemParamEnum.ERROR);
            JLogger.error(mess);
            return ResponseEntity.badRequest().body(responseService.response(mess));
        } else{
            return ResponseEntity.ok().body(responseService.response(r.getId(),r.getName(),r.getUsername(),r.getPassword(),r.getActive()));
        }
    }

//    啟用帳號
    @Operation(summary = "使用者啟用")
    @PutMapping("/active")
    public ResponseEntity<Map<String, Object>> active(@Valid @RequestBody AuthRequest authRequest,
                                       HttpServletRequest request) {
        String username = authRequest.getUsername();
        int password = authRequest.getPassword();
        Role r = roleService.findByUsername(username);

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if(roleService.findByUsernameAndPassword(username, password) == null){
            mess = responseService.getSysParams(SystemParamEnum.ERROR);
            JLogger.error(mess);
            return ResponseEntity.badRequest().body(responseService.response(mess));
        } else {
            r.setActive(1);
            roleService.save(r);
            mess = responseService.getSysParams(SystemParamEnum.SUCCESS);
            return ResponseEntity.ok().body(responseService.response(mess));
        }
    }

//    停用帳號
    @Operation(summary = "使用者停用")
    @PutMapping("/down")
    public ResponseEntity<Map<String, Object>> down(@Valid @RequestBody AuthRequest authRequest,
                                                    HttpServletRequest request) {
        String username = authRequest.getUsername();
        int password = authRequest.getPassword();
        Role r = roleService.findByUsername(username);

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if(roleService.findByUsernameAndPassword(username, password) == null){
            mess = responseService.getSysParams(SystemParamEnum.ERROR);
            JLogger.error(mess);
            return ResponseEntity.badRequest().body(responseService.response(mess));
        } else {
            r.setActive(0);
            roleService.save(r);
            mess = responseService.getSysParams(SystemParamEnum.SUCCESS);
            return ResponseEntity.ok().body(responseService.response(mess));
        }
    }
//    ----------------------權限-------------------------
//    更改權限
    @Operation(summary = "更改使用者權限")
    @PostMapping("/admin/updatePer")
    public ResponseEntity<Map<String, Object>> updatePer(@Valid @RequestBody UpdatePerRequest up,
                                                         HttpServletRequest request) {
        String username = up.getUsername();
        int password = up.getPassword();
        String target = up.getTarget();
        String permission = up.getPermission();
        Role r = roleService.findByUsernameAndPassword(username, password);

        if(jwtService.valid(request)) {
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if(r == null){
            mess = responseService.getSysParams(SystemParamEnum.ERROR);
            JLogger.error(mess);
            return ResponseEntity.badRequest().body(responseService.response(mess));
        } else {
            Permission p = permissionService.findByUsername(r.getUsername());
            if (r.getActive() == 0) {
                mess = responseService.getSysParams(SystemParamEnum.ACCOUNTNOUP);
            } else if (p.getRole().equals("admin")) {
                if (target.equals("me")) {
                    permissionService.updateByUsername(permission, username);
                } else {
                    permissionService.updateByUsername(permission, target);
                }
                mess = responseService.getSysParams(SystemParamEnum.SUCCESS);
                return ResponseEntity.ok().body(responseService.response(mess));
            } else {
                mess = responseService.getSysParams(SystemParamEnum.ACCOUNTNOPERMISSION);
            }
            JLogger.error(mess);
            return ResponseEntity.badRequest().body(responseService.response(mess));
        }
    }

//    刪除權限
    @Operation(summary = "刪除使用者權限")
    @PostMapping("/admin/deletePer")
    public ResponseEntity<Map<String, Object>> deletePer(@Valid @RequestBody DeletePerRequest dp,
                                                         HttpServletRequest request) {
        String username = dp.getUsername();
        int password = dp.getPassword();
        String target = dp.getTarget();
        Role r = roleService.findByUsernameAndPassword(username, password);

        if(jwtService.valid(request)){
            mess = responseService.getSysParams(SystemParamEnum.NOAUTHORIZED);
            JLogger.error(mess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        } else if(r == null){
            mess = responseService.getSysParams(SystemParamEnum.ERROR);
        } else {
            Permission p = permissionService.findByUsername(username);
            if (r.getActive() == 0) {
                mess = responseService.getSysParams(SystemParamEnum.ACCOUNTNOUP);
            } else if (p.getRole().equals("guest")) {
                mess = responseService.getSysParams(SystemParamEnum.ACCOUNTNOPERMISSION);
            } else {
                if (target.equals("me")) {
                    permissionService.deleteByUsername(username);
                } else {
                    permissionService.deleteByUsername(target);
                }
                mess = responseService.getSysParams(SystemParamEnum.SUCCESS);
                return ResponseEntity.ok().body(responseService.response(mess));
            }
        }
        JLogger.error(mess);
        return ResponseEntity.badRequest().body(responseService.response(mess));
    }
}
