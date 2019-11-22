package com.produit.services;

import com.produit.models.AppUser;
import com.produit.models.Produit;

import java.util.List;

public interface UserServices {

    AppUser findbyEmail(String email);

    void createUser(String email, String password, String role);

    void deleteAll();

}
