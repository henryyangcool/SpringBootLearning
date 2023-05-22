package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.service.GoogleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service("GoogleService")
public class GoogleServiceImpl implements GoogleService {

    @Value("${googleQrcodeFormat}")
    String GOOGLEQRCODEFORMAT;
    @Value("${otpType}")
    String otpType;
    @Value("${account}")
    String account;
    @Value("${issuer}")
    private static final String issuer = "AAA";
    @Value("${format}")
    String format;

    @Override
    public String createGoogleAuthenticatorKeyUrl(String secret) {
        return String.format(format,otpType,account,secret,issuer);
    }

    @Override
    public String createGoogleAuthQRCodeData(String secret, String account) {
        URIBuilder uri = new URIBuilder().setScheme("otpauth").setHost("totp")
                .setPath("/" + issuer + ":" + account).setParameter("secret", secret);
        uri.setParameter("issuer", issuer);
        uri.setParameter("algorithm", "SHA256");
        uri.setParameter("digits", "6");
        uri.setParameter("period", "30");
        return uri.toString();
    }

    @Override
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response, String captcha) throws Exception {
        // 设置响应的类型为图片
        response.setContentType("image/jpeg");
        // 创建一个宽度为100，高度为50的图片缓冲区
        BufferedImage bi = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
        // 获取画笔对象
        Graphics2D g = bi.createGraphics();
        // 设置画笔颜色为白色
        g.setColor(Color.WHITE);
        // 填充背景色
        g.fillRect(0, 0, 100, 50);
        // 生成随机验证码
        captcha = generateCaptcha();
        // 将验证码保存到Session中
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);
        // 将验证码画到图片上
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 24));
        g.drawString(captcha, 10, 30);
        // 将图片写入响应流中
        ImageIO.write(bi, "JPEG", response.getOutputStream());

        // 刷新响应流
        response.getOutputStream().flush();
    }

    @Override
    public String generateCaptcha() {
        Random random = new Random();
        int num = random.nextInt(9000) + 1000;
        return String.valueOf(num);
    }
}
