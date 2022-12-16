package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Commande;
import com.proj.ecommerce.model.PaiementCommande;
import com.proj.ecommerce.model.Statut;
import com.proj.ecommerce.projection.IArticleItem;
import com.proj.ecommerce.repository.ICommandeArticleRepository;
import com.proj.ecommerce.repository.ICommandeRepository;
import com.proj.ecommerce.repository.IPaiementCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementCommandeService implements IPaiementCommandeService{
       @Autowired
       IPaiementCommandeRepository paiementCommandeRepository;

       @Autowired
       ICommandeRepository commandeRepository;

       @Autowired
       ICommandeArticleRepository commandeArticleRepository;

       @Override
       public PaiementCommande savePaiementCommande(PaiementCommande paiementCommande) throws Exception {

               if (paiementCommande.getIdCommande()==null){
                   throw new Exception("Impossible d'effectuer un paiemeent sur une commande inexistante.");
               }
               List<IArticleItem> articleItems = commandeArticleRepository.findArticlesByCommandeId(paiementCommande.getIdCommande());
               if (articleItems.isEmpty()){
                   throw new Exception("Cette commande n'existe pas.");
               }
               Optional<Commande> commande = commandeRepository.findById(paiementCommande.getIdCommande());
               for (int i = 0 ; i<articleItems.size(); i++){
                   Long prixCommande =+ articleItems.get(i).getQuantite() * articleItems.get(i).getPrice();
                   paiementCommande.setPrixCommande(prixCommande);
               }
               paiementCommande.setCommande(commande.get());
               paiementCommande.setCommandeDate(LocalDateTime.now());
               paiementCommande.setStatut(Statut.PAYE);
               return paiementCommandeRepository.save(paiementCommande);
       }
}
