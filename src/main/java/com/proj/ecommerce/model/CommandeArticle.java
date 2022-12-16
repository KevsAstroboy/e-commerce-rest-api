package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "article_commandes")
public class CommandeArticle {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToOne
       @JoinColumn(name = "article_id",referencedColumnName = "id_article")
       private Article article;

       @ManyToOne
       @JoinColumn(name = "commande_id",referencedColumnName = "id_commande")
       private Commande commande;

       @Column(name = "quantite_article",nullable = false)
       private Long quantiteArticle;

       @Column(name = "prix_achat",nullable = false)
       private Long prixAchat;

       @Transient
       private Long articleId;

       @Transient
       private Long commandeId;
}
