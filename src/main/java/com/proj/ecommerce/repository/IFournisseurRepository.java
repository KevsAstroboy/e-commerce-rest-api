package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFournisseurRepository extends JpaRepository<Fournisseur,Long> {


       Optional<Fournisseur> findByName(String fournisseurName);
}
