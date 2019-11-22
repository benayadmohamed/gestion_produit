package com.produit.services;

import com.produit.models.Produit;

import java.util.List;

public interface ProduitServices {
    List<Produit> findAll();

    Produit findbyId(long id);

    void save(Produit produit);

    void delete(long id);

    public int getPageCourante();

    public int getSize();

    public String getMocle();

}
