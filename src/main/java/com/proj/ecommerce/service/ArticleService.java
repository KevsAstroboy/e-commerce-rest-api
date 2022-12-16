package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Article;
import com.proj.ecommerce.model.Categorie;
import com.proj.ecommerce.repository.IArticleRepository;
import com.proj.ecommerce.repository.ICategorieRepository;
import com.proj.ecommerce.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleService implements IArticleService{

       @Autowired
       private IArticleRepository articleRepository;

       @Autowired
       private ICategorieRepository categorieRepository;

       @Override
       public Article saveArticle(Article article) throws Exception {

              /*if (article.getCategorie()==null){
                  throw new Exception("L'ajout d'article a echoué");
              }*/
              System.out.println(article.getIdCategorie());
              System.out.println(article.getPrixUnitaire());
              if (!categorieRepository.findById(article.getIdCategorie()).isPresent()){
                     throw new Exception("Cette catégorie n'existe pas.");
              }
              Optional<Categorie> categorie = categorieRepository.findById(article.getIdCategorie());
              article.setCategorie(categorie.get());
           return articleRepository.save(article);
       }

       @Override
       public void deleteArticle(Long articleId){

              articleRepository.deleteById(articleId);
       }

       @Override
       public Article setPromotionOnArticle(Article article, Long articleId) throws Exception {
              DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
              if (!articleRepository.findById(articleId).isPresent()){
                     throw new Exception("L'article n'existe pas");
              }
              if (!SecurityUtils.isValid(article.getDateDebutPromo())|| !SecurityUtils.isValid(article.getDateFinPromo())){
                    throw new Exception("Le format de la date est incorrect");
              }
              Optional<Article> articl = articleRepository.findById(articleId);
              if (articl==null || !(LocalDate.parse(article.getDateDebutPromo(),df).isBefore(LocalDate.parse(article.getDateFinPromo(),df)))){

                      throw new Exception("Une promotion ne peut pas etre effectuer sur cet article. ");
              }

              articl.get().setDateDebutPromo(LocalDate.parse(article.getDateDebutPromo(),df).toString());
              articl.get().setDateFinPromo(LocalDate.parse(article.getDateFinPromo(),df).toString());
              articl.get().setPrixPromo(article.getPrixPromo());
            return articleRepository.save(articl.get());
       }

       @Override
       public List<Article> getAllArticleByCategorie(String categorieName) throws Exception {

              if (!categorieRepository.findByCategorieName(categorieName).isPresent()){
                     throw new Exception("Cette catégorie n'existe pas");
              }

              return articleRepository.getAllArticleByCategorie(categorieName);
       }


}
