package com.proj.ecommerce.controller;


import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class InternalApiController {

       @Autowired
       IUserService userService;

       @PutMapping("make-admin/{username}")
       public ResponseEntity<?> makeAdmin(@PathVariable("username") String username) throws Exception {

              ResponseEntity<?> response = null;

              try {
                     userService.makeAdmin(username);
                     response = ResponseEntity.status(HttpStatus.OK).body(response);
              } catch (Exception e) {
                     response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
              }

              return response;
       }
}
