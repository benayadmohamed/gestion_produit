package com.produit.repositories;

import com.produit.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepositories extends JpaRepository<Produit, Long> {
}
