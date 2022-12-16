package com.proj.ecommerce.service;

import com.proj.ecommerce.model.PaiementCommande;

public interface IPaiementCommandeService {
    PaiementCommande savePaiementCommande(PaiementCommande paiementCommande) throws Exception;
}
