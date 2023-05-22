package com.example.springbootlearning.Config;

//import com.develop.service.JWT.JWTAuthenticationTokenFilter;
//import com.develop.Config.JWT.JwtAuthAccessDenied;
//import com.develop.Config.JWT.JwtAuthEntryPoint;
//import com.develop.Config.JWT.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
//                任何請求都要登入
                .anyRequest().authenticated()
                .and()
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthenticationfilter, BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .formLogin()
//                    .loginProcessingUrl("/addUser")
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/index")
                .and()
                .csrf().disable();

        return http.build();
    }
//    @Autowired
//    private JwtAuthEntryPoint jwtAuthEntryPoint;
//
//    @Autowired
//    private JwtAuthAccessDenied jwtAuthAccessDenied;
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Value("#{'${whitelist}'.split(',')}")
//    String[] AUTH_WHITELIST;
//
//    boolean securityDebug = true;
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.debug(securityDebug)
//                .ignoring()
//                .requestMatchers(AUTH_WHITELIST);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .cors()
//                .and()
//                .exceptionHandling()
////        未經允許訪問
//                .authenticationEntryPoint(jwtAuthEntryPoint)
////        訪問被拒絕
//                .accessDeniedHandler(jwtAuthAccessDenied)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    //    增加使用者訊息
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails u1 = User.withUsername("admin")
////                .password("$2a$10$UVOyYuvI0BmPFMEDeg7cPOM4E.2.QEMNFerEl/95YzzyzVmAr6SCK")
////                {noop}密碼不編碼
//                .password("{noop}1111")
//                .roles("admin")
//                .build();
//        UserDetails u2 = User.withUsername("guest")
////                .password("$2a$10$UVOyYuvI0BmPFMEDeg7cPOM4E.2.QEMNFerEl/95YzzyzVmAr6SCK")
//                .password("{noop}2222")
//                .roles("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(u1, u2);
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin123").roles("ADMIN")
//                .and()
//                .withUser("user").password("{noop}user123").roles("USER");
//    }
}





