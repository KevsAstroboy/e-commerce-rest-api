package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.PaiementLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaiementLivraisonRepository extends JpaRepository<PaiementLivraison,Long> {



}
