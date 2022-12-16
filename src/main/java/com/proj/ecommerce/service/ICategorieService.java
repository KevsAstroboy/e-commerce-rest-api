package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Categorie;

public interface ICategorieService {
    Categorie addCategorie(Categorie categorie) throws Exception;

    void updateCategorie(Long categorieId, String categorieName);

    void deleteCategorie(Long categorieId);
}
