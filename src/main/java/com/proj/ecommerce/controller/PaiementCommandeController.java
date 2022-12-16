package com.proj.ecommerce.controller;

import com.proj.ecommerce.model.Article;
import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.model.PaiementCommande;
import com.proj.ecommerce.service.IPaiementCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/paiementCommande")
public class PaiementCommandeController {

       @Autowired
       IPaiementCommandeService paiementCommandeService;

    @PostMapping
    public ResponseEntity<?> savePaiementCommande(@RequestBody PaiementCommande paiementCommande){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.CREATED).body(paiementCommandeService.savePaiementCommande(paiementCommande));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }
}
