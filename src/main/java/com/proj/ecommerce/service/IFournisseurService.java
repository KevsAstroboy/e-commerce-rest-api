package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Fournisseur;

public interface IFournisseurService {
    Fournisseur saveFournisseur(Fournisseur fournisseur) throws Exception;

    void deleteFournisseur(Long fournisseurId) throws Exception;
}
