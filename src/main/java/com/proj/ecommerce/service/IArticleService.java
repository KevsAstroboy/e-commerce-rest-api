package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface IArticleService {



    Article saveArticle(Article article) throws Exception;

    void deleteArticle(Long articleId);


    Article setPromotionOnArticle(Article article, Long articleId) throws Exception;

    List<Article> getAllArticleByCategorie(String categorieName) throws Exception;
}
