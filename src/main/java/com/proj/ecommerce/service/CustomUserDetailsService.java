package com.proj.ecommerce.service;

import com.proj.ecommerce.model.User;
import com.proj.ecommerce.model.UserPrincipal;
import com.proj.ecommerce.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = null;
        try {
            Optional<User> user = userService.findByUsername(username);
            Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.get().getRole().name()));
             userPrincipal = UserPrincipal.builder()
                    .user(user.get())
                     .username(username)
                    .authorities(authorities)
                    .password(user.get().getPassword())
                    .idUser(user.get().getIdUser())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

     return userPrincipal;
    }
}
