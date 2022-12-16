package com.proj.ecommerce.service;

import com.proj.ecommerce.model.CommandeArticle;
import com.proj.ecommerce.projection.IArticleItem;

import java.util.List;

public interface ICommandeArticleService {
    CommandeArticle saveCommandeArticle(CommandeArticle commandeArticle) throws Exception;



    List<IArticleItem> getAllArticleByCommande(Long commandeId) throws Exception;
}
