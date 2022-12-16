package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categorie")
public class Categorie {


       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id_categorie")
       private Long id;

       @Column(name = "categorie_name",nullable = false)
       private String categorieName;

       @Column(name = "categorie_description")
       private String categorieDescription;


}
