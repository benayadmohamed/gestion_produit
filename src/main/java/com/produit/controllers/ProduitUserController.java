package com.produit.controllers;

import com.produit.models.Produit;
import com.produit.services.ProduitServices;
import com.produit.services.ProduitServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProduitUserController {

    private final ProduitServices produitServices;

    @Autowired
    public ProduitUserController(ProduitServices produitServices) {
        this.produitServices = produitServices;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute("listProduits")
    public List<Produit> listProduits() {
        return produitServices.findAll();
    }

    @ModelAttribute("pageCourante")
    public int pageCourante() {
        return produitServices.getPageCourante();
    }

    @ModelAttribute("size")
    public int size() {
        return produitServices.getSize();
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @ModelAttribute("motCle")
    public String motCle() {
        return produitServices.getMocle();
    }
}
