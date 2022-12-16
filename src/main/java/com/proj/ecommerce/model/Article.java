package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Table(name = "article")
@Entity
public class Article {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id_article")
       private Long idArticle;

       @Column(name = "article_description",nullable = false)
       private String description;

       @Column(name = "prix_unitaire",nullable = false)
       private Long prixUnitaire;

       @Column(name = "article_title",nullable = false)
       private String articleTitle;

       @Column(name = "article_photo")
       private String articlePhoto;

       @Column(name = "date_debut_promo")
       private String dateDebutPromo;

       @Column(name = "date_fin_promo")
       private String dateFinPromo;

       @Column(name = "prix_promo")
       private Long prixPromo;

       @Column(name = "article_en_stock")
       private Long articleEnStock;

       /*@ManyToMany
       //@JoinColumn(name = "commande_id",referencedColumnName = "id_commande")
       @JoinTable(
               name = "article_commandes",
               joinColumns = @JoinColumn(name = "id_article"),
               inverseJoinColumns = @JoinColumn(name = "id_commande"))
       private List<Commande> commandes;*/


       @ManyToOne
       @JoinColumn(name = "categorie_id",referencedColumnName = "id_categorie")
       private Categorie categorie;

       @Transient
       private  Long idCategorie;
}
