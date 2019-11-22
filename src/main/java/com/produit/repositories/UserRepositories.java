package com.produit.repositories;

import com.produit.models.AppUser;
import com.produit.models.Produit;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
