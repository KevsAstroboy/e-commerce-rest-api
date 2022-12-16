package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "livraison")
public class Livraison {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY )
       @Column(name = "id_livraison")
       private Long id;

       @Column(name = "date_livraison")
       private LocalDateTime dateLivraison;

       @Column(name = "livraison_lieu")
       private String lieuLivraison;

       @ManyToOne
       @JoinColumn(name = "article_id",referencedColumnName = "id_article",nullable = false)
       private Article article;

       @ManyToOne
       @JoinColumn(name = "fournisseur_id",referencedColumnName = "id_fournisseur")
       private Fournisseur fournisseur;

       @ManyToOne
       @JoinColumn(name = "paiement_livraison_id",referencedColumnName = "id_paiement_livraison",nullable = true)
       private PaiementLivraison paiementLivraison;

       @Transient
       private Long articleId;

       @Transient
       private Long fournisseurId;

       @Transient
       private Long paiementLivraisonId;

       @Transient
       private List<Long> idLivraison;




}
