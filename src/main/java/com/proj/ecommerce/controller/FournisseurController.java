package com.proj.ecommerce.controller;


import com.proj.ecommerce.model.Categorie;
import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.model.Fournisseur;
import com.proj.ecommerce.service.IFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fournisseur")
public class FournisseurController {

       @Autowired
       IFournisseurService fournisseurService;

    @PostMapping
    public ResponseEntity<?> saveFournisseur(@RequestBody Fournisseur fournisseur){

        ResponseEntity<Object> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.CREATED).body(fournisseurService.saveFournisseur(fournisseur));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;

    }

    @DeleteMapping("{fournisseurId}")
    public ResponseEntity<?> deleteFournisseur(@PathVariable Long fournisseurId){

        ResponseEntity<Object> response = null;
        try {
            fournisseurService.deleteFournisseur(fournisseurId);
            response = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
        }
        return response;

    }


}
