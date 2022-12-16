package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "commande")
public class Commande {


       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id_commande")
       private Long idCommande;

       @Column(name = "date_commande",nullable = false)
       private LocalDateTime dateCommande;

       @Column(name = "statut_commande",nullable = false)
       @Enumerated(EnumType.STRING)
       private Statut statutCommande;

       /*@ManyToMany(mappedBy = "commandes")
       private List<Article> arcticles;*/
       
       @OneToOne
       @JoinColumn(name = "paiement_commande_id",referencedColumnName = "id_paiement_commande")
       private PaiementCommande paiementCommande;

       @ManyToOne
       @JoinColumn(name = "user_id",referencedColumnName = "id_user",nullable = false)
       private User user;

       @Transient
       private Long userId;

       @Transient
       private String dateTime;

}
