package com.proj.ecommerce.controller;

import com.proj.ecommerce.model.Commande;
import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.service.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/commande")
public class CommandeController {

       @Autowired
       ICommandeService commandeService;

       @PostMapping
       public ResponseEntity<?> doCommande(@RequestBody Commande commande){

              ResponseEntity<Object> response = null;

           try {
               response = ResponseEntity.status(HttpStatus.CREATED).body(commandeService.saveCommande(commande));
           } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
           }
           return response;

       }



    @PutMapping("{commandeId}")
    public ResponseEntity<?> setCommandePayStatement( @PathVariable Long commandeId){

        ResponseEntity<Object> response = null;

        try {
            commandeService.setCommandePayStatement(commandeId);
            response = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;

    }

    @PutMapping("cancel/{commandeId}")
    public ResponseEntity<?> cancelCommande(@PathVariable Long commandeId){

        ResponseEntity<Object> response = null;

        try {
            commandeService.cancelCommande(commandeId);
            response = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;

    }






}
