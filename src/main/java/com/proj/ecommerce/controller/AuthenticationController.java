package com.proj.ecommerce.controller;


import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.model.User;
import com.proj.ecommerce.service.IAuthenticationService;
import com.proj.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) throws Exception {

           ResponseEntity<Object> response = null;

           try {

               response = ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));

           }catch (Exception e){

             response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
           }



        return response;
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){

        ResponseEntity<Object> response = null;

        try {

            response = ResponseEntity.status(HttpStatus.OK).body(authenticationService.signInAndReturnJWT(user));

        }catch (BadCredentialsException e){

            String errorMessage = "nom utilisateur ou mot de passe incorrect";

            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,errorMessage));
        }

        return response;
    }

}
