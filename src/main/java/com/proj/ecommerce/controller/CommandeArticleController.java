package com.proj.ecommerce.controller;

import com.proj.ecommerce.model.Commande;
import com.proj.ecommerce.model.CommandeArticle;
import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.service.ICommandeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/commandeArticle")
public class CommandeArticleController {

       @Autowired
       ICommandeArticleService commandeArticleService;

    @PostMapping
    public ResponseEntity<?> saveCommandeArticle(@RequestBody CommandeArticle commandeArticle){

        ResponseEntity<Object> response = null;

        try {
            response = ResponseEntity.status(HttpStatus.CREATED).body(commandeArticleService.saveCommandeArticle(commandeArticle));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;

    }

    @GetMapping("{commandeId}")
    public ResponseEntity<?> getAllArticleByCommande(@PathVariable Long commandeId){

        ResponseEntity<Object> response = null;

        try {
            response = ResponseEntity.status(HttpStatus.OK).body(commandeArticleService.getAllArticleByCommande(commandeId));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;

    }


}
