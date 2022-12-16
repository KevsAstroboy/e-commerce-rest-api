package com.proj.ecommerce.controller;

import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.model.Livraison;
import com.proj.ecommerce.model.PaiementLivraison;
import com.proj.ecommerce.service.IPaiementLivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/paiementLivraison")
public class PaiementLivraisonController {

       @Autowired
       IPaiementLivraisonService paiementLivraisonService;

    @PostMapping
    public ResponseEntity<?> savePaiementLivraison(@RequestBody PaiementLivraison paiementLivraison){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.CREATED).body(paiementLivraisonService.savePaiementLivraison(paiementLivraison));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }
}
