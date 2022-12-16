package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.CommandeArticle;
import com.proj.ecommerce.projection.IArticleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommandeArticleRepository extends JpaRepository<CommandeArticle,Long> {


       @Query(value = "select b.article_title as title, a.prix_achat as price , a.quantite_article as quantite from article_commandes a left join article b on a.article_id = b.id_article where commande_id = :commandeId",nativeQuery = true)
       List<IArticleItem> findArticlesByCommandeId(@Param("commandeId") Long commandeId);


}
