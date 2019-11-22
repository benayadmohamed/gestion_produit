package com.produit.repositories;

import com.produit.models.AppUser;
import com.produit.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositories extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
