package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IArticleRepository extends JpaRepository<Article,Long> {

       List<Article> findByArticleTitle(String articleTitle);

       @Query(value = "select * from Article a left join Categorie c on a.categorie_id = c.id_categorie where c.categorie_name = :categorieName",nativeQuery = true)
       List<Article> getAllArticleByCategorie(@Param("categorieName") String categorieName);

       @Query("update Article set articleEnStock = :articleEnStock where idArticle = :articleId")
       @Modifying
       void updateArticleInStock(@Param("articleId") Long articleId , @Param("articleEnStock") Long articleEnStock );


}
