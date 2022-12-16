package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Fournisseur;
import com.proj.ecommerce.repository.IFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class FournisseurService implements IFournisseurService{

        @Autowired
        IFournisseurRepository fournisseurRepository;

        public static boolean patternMatches(String emailAddress) {
               String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                                   + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
               return Pattern.compile(regexPattern)
                             .matcher(emailAddress)
                             .matches();
        }

        @Override
       public Fournisseur saveFournisseur(Fournisseur fournisseur) throws Exception {

              if (fournisseurRepository.findByName(fournisseur.getName()).isPresent()){
                  throw new Exception("Ce fournisseur existe déja.");
              }

             if (!patternMatches(fournisseur.getEmail())){

               throw new Exception("  L'addresse email entrée  n'est pas valide. ");
             }
             return fournisseurRepository.save(fournisseur);
       }

       @Override
       public  void deleteFournisseur(Long fournisseurId) throws Exception {
                if (!fournisseurRepository.findById(fournisseurId).isPresent()){
                    throw new Exception("Ce fournisseur n'existe pas.");
                }
                fournisseurRepository.deleteById(fournisseurId);
       }



}
