package com.proj.ecommerce.service;

import com.proj.ecommerce.model.User;
import com.proj.ecommerce.model.UserPrincipal;
import com.proj.ecommerce.security.jwt.IJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {


       @Autowired
       private AuthenticationManager authenticationManager;

       @Autowired
       private IJwtProvider jwtProvider;

       @Override
       public User signInAndReturnJWT(User signInRequest){

              User userSignIn = null;

              Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword()));

              if (authentication != null){

                  UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

                  String jwt = jwtProvider.generateToken(userPrincipal);

                  userSignIn = userPrincipal.getUser();

                  userSignIn.setToken(jwt);
              }

              return userSignIn;


        }

}
