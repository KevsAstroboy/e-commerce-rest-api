package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Livraison;

import java.util.List;

public interface ILivraisonService {
    Livraison saveLivraison(Livraison livraison) throws Exception;

    void payLivraison(List<Long> idLivraison, Long idPaiementLivraison) throws Exception;

    List<Livraison> getAllLivraisonNotPay();
}
