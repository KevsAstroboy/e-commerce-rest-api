package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Categorie;
import com.proj.ecommerce.repository.ICategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class CategorieService implements ICategorieService
{

    @Autowired
    ICategorieRepository categorieRepository;


    @Override
    public Categorie addCategorie(Categorie categorie) throws Exception {
           if (!StringUtils.hasLength(categorie.getCategorieName())){
               throw new Exception("Veuillez bien remplir vos différents champs.");
           }

           if (categorieRepository.findByCategorieName(categorie.getCategorieName()).isPresent()){

               throw new Exception("Cette catégorie existe déja.");
        }

           return categorieRepository.save(categorie);
    }

    @Override
    @Transactional
    public void updateCategorie(Long categorieId, String categorieName) {


           categorieRepository.updateCategorie(categorieId,categorieName);
    }

    @Override
    public void deleteCategorie(Long categorieId){

           categorieRepository.deleteById(categorieId);
    }
}
