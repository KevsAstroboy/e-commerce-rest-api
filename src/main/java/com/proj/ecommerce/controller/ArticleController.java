package com.proj.ecommerce.controller;


import com.proj.ecommerce.model.Article;
import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/article")
public class ArticleController {

       @Autowired
       IArticleService articleService;

       @PostMapping
       public ResponseEntity<?> saveArticle(@RequestBody Article article){

           ResponseEntity<Object> response = null;
           try {
               response = ResponseEntity.status(HttpStatus.CREATED).body(articleService.saveArticle(article));
           } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
           }
           return response;
       }

    @DeleteMapping("{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long articleId){

        ResponseEntity<Object> response = null;

        try {
            articleService.deleteArticle(articleId);
            response = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> setPromotionOnArticle(@RequestBody Article article , @PathVariable("articleId") Long articleId){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.CREATED).body(articleService.setPromotionOnArticle(article,articleId));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }


    @GetMapping("{categorieName}")
    public ResponseEntity<?> getAllArticleByCategorieName(@PathVariable String categorieName){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticleByCategorie(categorieName));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }

}
