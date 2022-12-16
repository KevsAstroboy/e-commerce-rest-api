package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "paiement_commande")
public class PaiementCommande {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id_paiement_commande")
       private Long id;

       @Column(name = "paiement_commande_date")
       private LocalDateTime commandeDate;

       @Column(name = "paiement_prix")
       private Long prixCommande;

       @Column(name = "statut_paiement")
       @Enumerated(EnumType.STRING)
       private Statut statut;

       @OneToOne
       @JoinColumn(name = "commande_id",referencedColumnName = "id_commande",nullable = false)
       private Commande commande;

       @Transient
       private Long idCommande;
}
