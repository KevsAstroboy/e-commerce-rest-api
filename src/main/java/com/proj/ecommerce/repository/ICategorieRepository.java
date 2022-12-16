package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategorieRepository extends JpaRepository<Categorie,Long> {

        @Modifying
        @Query("update Categorie set categorieName = :categorieName where id = :categorieId")
        void updateCategorie(@Param("categorieId") Long categorieId ,@Param("categorieName")String categorieName);

        Optional<Categorie> findByCategorieName(@Param("categorieName") String categorieName);

}
