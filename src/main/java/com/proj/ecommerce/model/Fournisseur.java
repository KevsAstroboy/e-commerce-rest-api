package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "fournisseur")
public class Fournisseur {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id_fournisseur")
       private Long id;

       @Column(name = "name",nullable = false)
       private String name;

       @Column(name = "email")
       private String email;

       @Column(name = "numero_tel")
       private String numeroTel;


}
