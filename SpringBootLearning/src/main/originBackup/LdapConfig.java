//package com.develop.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
//import org.springframework.ldap.core.ContextSource;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.ldap.core.support.LdapContextSource;
//
//@Configuration
////@EnableLdapRepositories(basePackages = "com.develop.Repository")
//public class LdapConfig {
//    @Bean
//    public ContextSource contextSource(){
//        LdapContextSource ldapContextSource = new LdapContextSource();
//        ldapContextSource.setUrl("ldap://localhost:389/");
//        ldapContextSource.setBase("dc=FET,dc=com");
//        ldapContextSource.setUserDn("cn=Manager,dc=FET,dc=com");
//        ldapContextSource.setPassword("secret");
//
//        return ldapContextSource;
//    }
//
//    @Bean
//    public LdapTemplate ldapTemplate(){
//        return new LdapTemplate(contextSource());
//    }
//}
