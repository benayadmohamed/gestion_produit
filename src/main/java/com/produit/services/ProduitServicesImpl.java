package com.produit.services;

import com.produit.models.Produit;
import com.produit.repositories.ProduitRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProduitServicesImpl implements ProduitServices {

    private final ProduitRepositories produitRepositories;

    private int pageCourante;
    private int size;
    private String mocle;

    @Autowired
    public ProduitServicesImpl(ProduitRepositories produitRepositories) {
        this.produitRepositories = produitRepositories;
    }

    public List<Produit> findAll() {
        return produitRepositories.findAll();

    }

    @Override
    public Produit findbyId(long id) {
        return produitRepositories.findById(id).get();
    }

    @Override
    public void save(Produit produit) {
        this.produitRepositories.save(produit);
    }

    public void delete(long id) {
        produitRepositories.deleteById(id);
    }

    public int getPageCourante() {
        return pageCourante;
    }

    public int getSize() {
        return size;
    }

    public String getMocle() {
        return mocle;
    }
}
