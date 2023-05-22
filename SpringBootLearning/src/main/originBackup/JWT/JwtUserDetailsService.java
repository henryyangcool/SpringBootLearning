package com.develop.Config.JWT;

import com.develop.Entity.Role;
import com.develop.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private RoleService roleService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 在 UserRepository 中查詢用戶信息
    Role r = roleService.findByUsername(username);

    if (r == null) {
      throw new UsernameNotFoundException("用戶名不存在");
    }

    // 返回一個 UserDetails 對象，用於身份驗證和授權
    return new org.springframework.security.core.userdetails.User(r.getUsername(), r.getPassword(),
            getAuthorities(r));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(User user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    // 添加用戶的權限信息
    for (Role role : etRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }
}
