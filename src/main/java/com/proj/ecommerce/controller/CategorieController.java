package com.proj.ecommerce.controller;


import com.proj.ecommerce.model.Categorie;
import com.proj.ecommerce.model.Error;
import com.proj.ecommerce.service.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categorie")
public class CategorieController {

       @Autowired
       ICategorieService categorieService;



       @PostMapping
       public ResponseEntity<?> saveCategorie(@RequestBody Categorie categorie){

              ResponseEntity<Object> response = null;
              try {
                  response = ResponseEntity.status(HttpStatus.CREATED).body(categorieService.addCategorie(categorie));
              } catch (Exception e) {
                  response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
              }
              return response;

       }

       @PutMapping("{categorieId}/{categorieName}")
       public ResponseEntity<?> updateCatgorie(@PathVariable Long categorieId,@PathVariable String categorieName){

              ResponseEntity<Object> response = null;

              try {
                     categorieService.updateCategorie(categorieId,categorieName);
                     response = ResponseEntity.status(HttpStatus.OK).body(response);
              } catch (Exception e) {
                     response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
              }
              return response;
       }

       @DeleteMapping("{categorieId}")
       public ResponseEntity<?> deleteCatgorie(@PathVariable Long categorieId){

              ResponseEntity<Object> response = null;

              try {
                     categorieService.deleteCategorie(categorieId);
                     response = ResponseEntity.status(HttpStatus.OK).body(response);
              } catch (Exception e) {
                     response = ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(true,e.getMessage()));
              }
              return response;
       }
}
