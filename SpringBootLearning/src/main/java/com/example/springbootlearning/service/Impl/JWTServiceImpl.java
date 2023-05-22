package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Database.Entity.RefreshToken;
import com.example.springbootlearning.request.AuthRequest;
import com.example.springbootlearning.service.JWTService;
import com.example.springbootlearning.service.RoleService;
import io.jsonwebtoken.*;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;

//Test
@Service
public class JWTServiceImpl implements JWTService {

//    5分鐘
    private static final long EXPIRATION_TIME = 60000 * 130;
    private static final String SECRET = "YeeIsLearningSpringBootSpringdocJWTAndThisKeyIsNotEasyTooHack";

    @Autowired
    private RoleService roleService;

    @Override
    public String generateToken(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        int password = authRequest.getPassword();

        if(roleService.findByUsernameAndPassword(username, password) == null) {
            return "No Permission";
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("password", password);

        JwtBuilder builder = Jwts.builder()
                .setClaims( claims )
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS256, SECRET);

        return builder.compact();
    }
    @Override
    public String generateToken(String username) {
//        String username = googleAuthRequest.getUsername();

        if(roleService.findByUsername(username) == null) {
            return "No Permission";
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        JwtBuilder builder = Jwts.builder()
                .setClaims( claims )
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS256, SECRET);

        return builder.compact();
    }
    @Override
    public String generateToken(RefreshToken refreshToken) {
        String username = refreshToken.getUsername();
//        if(roleService.findByUsername(username) == null) {
//            return "No Permission";
//        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        JwtBuilder builder = Jwts.builder()
                .setClaims( claims )
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS256, SECRET);

        return builder.compact();
    }

    @Override
    public void validateToken(String token) throws AuthException {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);
        }
//        catch (SignatureException e) {
//            throw new AuthException("Invalid JWT signature.");
//        }
        catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        }
        catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token");
        }
        catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token");
        }
        catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid");
        }
    }

    public boolean valid(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header == null){
            return true;
        }else {
            String token = header.substring(6);
            try {
                validateToken(token);
                return false;
            } catch (AuthException e) {
                return true;
            }
        }
    }
    @Override
    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    @Override
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey("${secret}").parseClaimsJws(token).getBody();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

//    public String generateToken(String username, int password) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//        claims.put("password", password);
//        claims.put("password", password);
//
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//        JwtBuilder builder = Jwts.builder()
//                .setClaims( claims )
//                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME  ))
//                .signWith(key);
//
//        return builder.compact();
//    }

//    public String generateToken(AuthRequest request) {
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
//        authentication = authenticationManager.authenticate(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 2);
//
//        Claims claims = Jwts.claims();
//        claims.put("username", userDetails.getUsername());
//        claims.setExpiration(calendar.getTime());
//        claims.setIssuer("Programming Classroom");
//
//        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
//        JwtBuilder jwtBuilder = Jwts.builder()
//                .setClaims(claims)
//                .signWith(secretKey);
//
//        return jwtBuilder.compact();
//    }

//    public Claims parseToken(String token) {
//        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
//        System.out.println(secretKey);
//        JwtParser parser = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build();
//
//        Claims claims = parser
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.entrySet().stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
//    }

//    public boolean validateToken(String token) {
//        if (token == null) {
//            return false;
//        }
//        try {
//            Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
}

