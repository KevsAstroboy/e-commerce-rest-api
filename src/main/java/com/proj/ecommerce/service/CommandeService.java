package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Article;
import com.proj.ecommerce.model.Commande;
import com.proj.ecommerce.model.Statut;
import com.proj.ecommerce.model.User;
import com.proj.ecommerce.repository.IArticleRepository;
import com.proj.ecommerce.repository.ICommandeRepository;
import com.proj.ecommerce.repository.IUserRepository;
import com.proj.ecommerce.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService implements ICommandeService{

       @Autowired
       private ICommandeRepository commandeRepository;

       @Autowired
       private IArticleRepository articleRepository;

       @Autowired
       private IUserRepository userRepository;



       @Override
       public Commande saveCommande(Commande commande) throws Exception {

              if (!userRepository.findById(commande.getUserId()).isPresent()){
                     throw new Exception("Cet utilisateur n'existe pas.");
              }
              Optional<User> user = userRepository.findById(commande.getUserId());
              commande.setUser(user.get());
              commande.setStatutCommande(Statut.VALIDE);
              commande.setDateCommande(LocalDateTime.now());
              return commandeRepository.save(commande);
       }
       @Override
       public List<Commande> getAllCommandeOfUser(String username) throws Exception {

              if (!userRepository.findByUsername(username).isPresent() || username == null){
               throw new Exception("Utilisateur introuvable , veuillez verifier votre nom utilisateur.");
              }


              return commandeRepository.getAllCommandeOfUser(username);

       }
       @Override
       public List<Commande> getAllCommandeAtDate(String username, String dateTime) throws Exception {

              if (!StringUtils.hasLength(dateTime) || !SecurityUtils.isValid((dateTime))){
                     throw new Exception("Le format de la date n'est pas valide.");
              }
              if (!userRepository.findByUsername(username).isPresent() || username == null){
                     throw new Exception("Veuillez bien renseigner vos champs.");
              }

              return commandeRepository.getAllCommandeAtDate(username,dateTime);
       }

       @Override
       @Transactional
       public void setCommandePayStatement(Long commandeId) throws Exception {

              Optional<Commande> commande = commandeRepository.findById(commandeId);
              if (commande.get()==null){
                     throw  new Exception("La commande n'existe pas");
              }
              commandeRepository.setCommandePayStatement(commandeId,Statut.PAYE);

       }

       @Override
       @Transactional
       public void cancelCommande(Long commandeId) throws Exception {

              Optional<Commande> commande = commandeRepository.findById(commandeId);
              if (commande==null){
                     throw  new Exception("La commande n'existe pas");
              }
              commandeRepository.setCommandePayStatement(commandeId,Statut.ANNULE);
       }



}
