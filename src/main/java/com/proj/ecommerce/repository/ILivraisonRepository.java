package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILivraisonRepository extends JpaRepository<Livraison,Long> {

       @Query(value = "Update livraison set paiement_livraison_id = :idPaiementLivraison where id_livraison = :idLivraison ",nativeQuery = true)
       @Modifying
       void payLivraison(@Param("idLivraison") Long idLivraison,@Param("idPaiementLivraison") Long idPaiementLivraison);


       @Query(value = "Select * from livraison where paiement_livraison_id is null",nativeQuery = true)
       List<Livraison> getAllLivraisonNotPay();
}
