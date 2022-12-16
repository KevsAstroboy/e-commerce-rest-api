package com.proj.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "paiement_livraison")
public class PaiementLivraison {

             @Id
             @GeneratedValue(strategy = GenerationType.IDENTITY)
             @Column(name = "id_paiement_livraison")
             private Long id;

             @Column(name = "paiement_date",nullable = false)
             private LocalDateTime datePaiement;

             @Column(name = "statut_paiement")
             @Enumerated(EnumType.STRING)
             private Statut statut;

             @Column(name = "paiement_prix",nullable = false)
             private Long prixLivraison;
}
