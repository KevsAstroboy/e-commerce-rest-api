package com.proj.ecommerce.service;

import com.proj.ecommerce.model.PaiementLivraison;
import com.proj.ecommerce.model.Statut;
import com.proj.ecommerce.repository.IPaiementLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaiementLivraionService implements IPaiementLivraisonService {

       @Autowired
       private IPaiementLivraisonRepository paiementLivraisonRepository;


       @Override
       public PaiementLivraison savePaiementLivraison(PaiementLivraison paiementLivraison) throws Exception {


              if (paiementLivraison.getPrixLivraison()==null){
                  throw new Exception("Veuillez rentrer le prix de la livraison");
              }
              paiementLivraison.setDatePaiement(LocalDateTime.now());
              paiementLivraison.setStatut(Statut.PAYE);
              return paiementLivraisonRepository.save(paiementLivraison);
       }
}
