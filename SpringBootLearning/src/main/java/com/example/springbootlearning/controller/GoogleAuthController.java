package com.example.springbootlearning.controller;

import com.example.springbootlearning.Database.Entity.RefreshToken;
import com.example.springbootlearning.Enum.SystemParamEnum;
import com.example.springbootlearning.service.*;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Tag(name = "GoogleAuth")
@RestController
@RequestMapping("/api/google")
public class GoogleAuthController {

    @Value("${secret}")
    String SECRET;
    @Value("${googleQrcodeFormat}")
    String GOOGLEQRCODEFORMAT;
    @Value("${otpType}")
    String otpType;
    @Value("${account}")
    String account;
    @Value("${issuer}")
    String issuer;
    @Value("${format}")
    String format;
    String captcha;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private MailService mailService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private ApiandIPService apiandipService;
    @Autowired
    private GoogleService googleService;


    @GetMapping(value = "/qrcode")
    public RedirectView getQrcode(){
        String qrcodeData = googleService.createGoogleAuthenticatorKeyUrl(SECRET);
//        String qrcodeData = googleService.createGoogleAuthQRCodeData(SECRET, account);
        String qrCodeUrl = String.format(GOOGLEQRCODEFORMAT, qrcodeData);
        System.out.println(qrCodeUrl);

        return new RedirectView(qrCodeUrl);
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> googleAuthenticator(@RequestParam int code,
                                                                   @RequestParam String username,
                                                                   HttpServletResponse response){
        GoogleAuthenticator gAuth = new GoogleAuthenticator();

        boolean isCorrect = gAuth.authorize(SECRET, code);
        System.out.println(isCorrect);
        String token = jwtService.generateToken(username);

        if(isCorrect && !token.equals("No Permission")){
            mailService.mailSend("henryyangcool@gmail.com", "henryyang1@fareastone.com.tw", "Token告知", "發Token\n" + token);

            long id = roleService.findByUsername(username).getId();
            cookieService.addCookie(response, "TOKEN", token, id, username);

            return ResponseEntity.ok(responseService.response_Token(token));
        }else{
            String mess = responseService.getSysParams(SystemParamEnum.NOPERMISSION);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService.response(mess));
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Map<String, Object>> refreshToken(HttpServletRequest request, HttpServletResponse response){
        RefreshToken r = cookieService.getCookieData(request);

        if(r == null){
            String mess = responseService.getSysParams(SystemParamEnum.TOKENISNOTEXIST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseService.response(mess));
        }else {
            String newToken = jwtService.generateToken(r);
            String oldToken = r.getToken();
            cookieService.refreshCookie(request, response,"TOKEN", newToken, r.getUsername(), oldToken);
            mailService.mailSend("henryyangcool@gmail.com", "henryyang1@fareastone.com.tw", "Token告知", "更新Token\n" + newToken);

            return ResponseEntity.ok(responseService.response_Token(newToken));
        }
    }

    @GetMapping(value = "/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        googleService.getCaptcha(request, response, captcha);
    }

//    public String getSecretKey(){
//        GoogleAuthenticator googleAuth = new GoogleAuthenticator();
//        GoogleAuthenticatorKey key = googleAuth.createCredentials();
//        return key.getKey();
//    }
}
