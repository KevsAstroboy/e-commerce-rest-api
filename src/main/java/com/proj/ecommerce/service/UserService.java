package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Role;
import com.proj.ecommerce.model.User;
import com.proj.ecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService{



       @Autowired
       private IUserRepository userRepository;

       @Autowired
       private PasswordEncoder passwordEncoder;


       @Override
       public User saveUser(User user) throws Exception {

              if (user != null && userRepository.findByUsername(user.getUsername()).isPresent()){
                     throw new Exception(" Cet utilisteur existe déja , veuillez modifier votre nom utilisateur ");
              }
              if (!patternMatches(user.getEmail())){

                     throw new Exception("  L'addresse email entrée  n'est pas valide ");
              }

              user.setRole(Role.USER);
              user.setPassword(passwordEncoder.encode(user.getPassword()));
              return userRepository.save(user);

       }

       @Override
       public Optional<User> findByUsername(String username) throws BadCredentialsException {

              if (!userRepository.findByUsername(username).isPresent()){

                     throw new BadCredentialsException(" Cet utilisateur n'existe pas ");
              }

              return userRepository.findByUsername(username);
       }

       @Transactional
       @Override
       public void makeAdmin(String username) throws Exception {
              if (!userRepository.findByUsername(username).isPresent()){

                     throw new Exception(" Cet utilisateur n'existe pas ");
              }

              userRepository.makeAdmin(username,Role.ADMIN);
       }



       @Override
       public void deleteByUsername(String username) throws Exception {

              if (!userRepository.findByUsername(username).isPresent()){

                     throw new Exception(" Cet utilisateur n'existe pas ");
              }

              userRepository.deleteByUsername(username);
       }

    public static boolean patternMatches(String emailAddress) {
          String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                  + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}