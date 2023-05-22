package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Database.Entity.RefreshToken;
import com.example.springbootlearning.service.CookieService;
import com.example.springbootlearning.service.RefreshTokenService;
import com.example.springbootlearning.service.RoleService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CookieService")
 public class CookieServiceImpl implements CookieService {
    private static final String COOKIE_NAME = "TOKEN";
//    5min
    private static final int EXPIRE_TIME = 60 * 5;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private RoleService roleService;

    @Override
    public void addCookie(HttpServletResponse response, String name, String value, long id, String username) {

        RefreshToken r = new RefreshToken();
        r.setId(roleService.findByUsername(username).getId());
        r.setUsername(username);
        r.setToken(value);

        refreshTokenService.save(r);

        setCookie(response, name, value);
    }

    @Override
    public void refreshCookie(HttpServletRequest request, HttpServletResponse response,
                             String name, String value, String username, String oldToken) {

        deleteCookie(request, response, name);

        RefreshToken r = refreshTokenService.findByRefreshToken(oldToken);
        r.setUsername(username);
        r.setToken(value);

        refreshTokenService.save(r);

        setCookie(response, name, value);
    }

    void setCookie(HttpServletResponse response, String name, String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                cookieValue = cookie.getValue();
                break;
            }
        }

        if (cookieValue != null) {
            Cookie cookie = new Cookie(name, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    @Override
    public RefreshToken getCookieData(HttpServletRequest request){
        String refreshToken = getCookieValue(request, COOKIE_NAME);
        if (refreshToken == null) {
            return null;
        }
        return refreshTokenService.findByRefreshToken(refreshToken);
    }

    String getCookieValue(HttpServletRequest request, String cookie_name){
        if(request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookie_name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
