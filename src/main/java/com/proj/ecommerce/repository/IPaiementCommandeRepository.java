package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.PaiementCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaiementCommandeRepository extends JpaRepository<PaiementCommande,Long> {
}
