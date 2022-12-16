package com.proj.ecommerce.controller;

import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.model.Livraison;
import com.proj.ecommerce.model.PaiementCommande;
import com.proj.ecommerce.service.ILivraisonService;
import com.proj.ecommerce.service.LivraisonService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/livraison")
public class LivraisonController {

       @Autowired
       ILivraisonService livraisonService;


    @PostMapping
    public ResponseEntity<?> saveLivraison(@RequestBody Livraison livraison){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.CREATED).body(livraisonService.saveLivraison(livraison));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }

    @PutMapping("{idPaiementLivraison}")
    public ResponseEntity<?>  payLivraison(@RequestBody Livraison livraison,@PathVariable("idPaiementLivraison") Long idPaiementLivraison){

        ResponseEntity<Object> response = null;
        try {
            livraisonService.payLivraison(livraison.getIdLivraison(),idPaiementLivraison);
            response = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }
    @GetMapping
    public ResponseEntity<?>getAllLivraisonNotPay(){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.OK).body(livraisonService.getAllLivraisonNotPay());
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;
    }







}
