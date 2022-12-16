package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "user")
public class User {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id_user")
       private Long idUser;

       @Column(name = "username",unique = true,length = 100,nullable = false)
       private String username;

       @Column(name = "password",length = 100)
       private String password;

       @Column(name = "user_email",nullable = true,unique = true,length = 100)
       private String email;

       @Column(name = "name",nullable = false,length = 100)
       private String name;

       @Enumerated(EnumType.STRING)
       private Role role;

       @Transient
       private String nomUtilisateur;

       @Transient
       private String token;


}
