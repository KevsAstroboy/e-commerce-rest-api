package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Commande;

import java.time.LocalDateTime;
import java.util.List;

public interface ICommandeService {
    Commande saveCommande(Commande commande) throws Exception;

    List<Commande> getAllCommandeOfUser(String username) throws Exception;


    List<Commande> getAllCommandeAtDate(String username, String dateTime) throws Exception;

    void setCommandePayStatement(Long commandeId) throws Exception;

    void cancelCommande(Long commandeId) throws Exception;
}
