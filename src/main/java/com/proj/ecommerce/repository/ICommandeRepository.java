package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.Commande;
import com.proj.ecommerce.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICommandeRepository extends JpaRepository<Commande,Long> {

    @Query(value = "select * from Commande c left join User u on u.id_user = c.user_id where u.username = :username",nativeQuery = true)
    List<Commande> getAllCommandeOfUser(@Param("username") String username);

    @Query(value = "select * from Commande c left join User u on u.id_user = c.user_id where u.username = :username and DATE(c.date_commande) = DATE(:date) ",nativeQuery = true)
    List<Commande> getAllCommandeAtDate(@Param("username") String username, @Param("date") String date);

    @Query("Update Commande set statutCommande = :statut where idCommande = :idCommande")
    @Modifying
    void setCommandePayStatement(@Param("idCommande") Long idCommande,@Param("statut") Statut statut);


}
