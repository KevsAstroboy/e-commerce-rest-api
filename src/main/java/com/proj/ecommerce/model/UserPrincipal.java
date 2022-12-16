package com.proj.ecommerce.model;

import com.proj.ecommerce.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

       private Long idUser;

       private transient User user;

       private  String username;

       private transient String password;

       private Set<GrantedAuthority> authorities;


       public static UserPrincipal createAdmin(){

               Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(Role.ADMIN.name()));

               return UserPrincipal.builder()
                       .idUser(-1L)
                       .username("system-admin")
                       .authorities(authorities)
                       .build();
       }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
