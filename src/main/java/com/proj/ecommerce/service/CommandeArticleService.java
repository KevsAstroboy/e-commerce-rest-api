package com.proj.ecommerce.service;


import com.proj.ecommerce.model.Article;
import com.proj.ecommerce.model.CommandeArticle;
import com.proj.ecommerce.projection.IArticleItem;
import com.proj.ecommerce.repository.IArticleRepository;
import com.proj.ecommerce.repository.ICommandeArticleRepository;
import com.proj.ecommerce.repository.ICommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeArticleService implements ICommandeArticleService{
       @Autowired
       ICommandeArticleRepository commandeArticleRepository;

       @Autowired
       IArticleRepository articleRepository;

       @Autowired
       ICommandeRepository commandeRepository;


       @Override
       @Transactional
       public CommandeArticle saveCommandeArticle(CommandeArticle commandeArticle) throws Exception {

              if (commandeArticle.getCommandeId()==null || commandeArticle.getArticleId()==null ){
                     throw new Exception("Veuillez bien renseigner vos champs.");
              }
              if (!commandeRepository.findById(commandeArticle.getCommandeId()).isPresent() || !articleRepository.findById(commandeArticle.getArticleId()).isPresent()){
                     throw new Exception("L'article ou la commande n'existe pas.");
              }

              Optional<Article> article = articleRepository.findById(commandeArticle.getArticleId());
              if (!commandeArticle.getPrixAchat().equals(article.get().getPrixUnitaire()) && !commandeArticle.getPrixAchat().equals(article.get().getPrixPromo())){
                     throw new Exception("Ceci n'est pas le prix de l'article.");
              }
             /* if (article.get().getPrixPromo() != null && !commandeArticle.getPrixAchat().equals(article.get().getPrixPromo())){
                     throw new Exception("Ceci n'est pas le prix de l'article.");
              }*/
              articleRepository.updateArticleInStock(commandeArticle.getArticleId(),article.get().getArticleEnStock() - commandeArticle.getQuantiteArticle());
              commandeArticle.setArticle(article.get());
              commandeArticle.setCommande(commandeRepository.findById(commandeArticle.getCommandeId()).get());
              return commandeArticleRepository.save(commandeArticle);
       }

       @Override
       public List<IArticleItem> getAllArticleByCommande(Long commandeId) throws Exception {

              if (!commandeRepository.findById(commandeId).isPresent()){
                     throw new Exception("Cette commande n'existe pas.");
              }

              return commandeArticleRepository.findArticlesByCommandeId(commandeId);
       }




}
