package com.proj.ecommerce.service;


import com.proj.ecommerce.model.Livraison;
import com.proj.ecommerce.repository.IArticleRepository;
import com.proj.ecommerce.repository.IFournisseurRepository;
import com.proj.ecommerce.repository.ILivraisonRepository;
import com.proj.ecommerce.repository.IPaiementLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LivraisonService implements ILivraisonService{

       @Autowired
       ILivraisonRepository livraisonRepository;

       @Autowired
       IPaiementLivraisonRepository paiementLivraisonRepository;

       @Autowired
       IFournisseurRepository fournisseurRepository;

       @Autowired
       IArticleRepository articleRepository;


       @Override
       public Livraison saveLivraison(Livraison livraison) throws Exception {


              if (!StringUtils.hasLength(livraison.getLieuLivraison())){

                     throw new Exception("Veuillez renseigner le lieu de la livraison.");
              }
              if (!fournisseurRepository.findById(livraison.getFournisseurId()).isPresent()){
                     throw new Exception("Ce fournisseur n'existe pas.");
              }
              if (!articleRepository.findById(livraison.getArticleId()).isPresent()){
                     throw new Exception("Cet article n'existe pas.");
              }
              livraison.setArticle(articleRepository.findById(livraison.getArticleId()).get());
              livraison.setFournisseur(fournisseurRepository.findById(livraison.getFournisseurId()).get());
              livraison.setDateLivraison(LocalDateTime.now());
              return livraisonRepository.save(livraison);
       }

       @Override
       @Transactional
       public void payLivraison(List<Long> idLivraison, Long idPaiementLivraison) throws Exception {

              if (!paiementLivraisonRepository.findById(idPaiementLivraison).isPresent()){
                     throw new Exception("Ce paiement n'a pas été effectuer.");
              }

              for (Long livraisonId : idLivraison ){
                     if (!livraisonRepository.findById(livraisonId).isPresent()){
                            throw new Exception("Cette livraison n'existe pas.");
                     }
                     if (livraisonRepository.findById(livraisonId).get().getPaiementLivraison() != null){
                            throw new Exception("Cette livraison a déja été payé.");
                     }
                     livraisonRepository.payLivraison(livraisonId,idPaiementLivraison);
              }



       }

       @Override
       public List<Livraison> getAllLivraisonNotPay(){

              return livraisonRepository.getAllLivraisonNotPay();
       }
}
