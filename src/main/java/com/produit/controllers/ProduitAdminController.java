package com.produit.controllers;

import com.produit.models.Produit;
import com.produit.services.ProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin")
public class ProduitAdminController {

    private final ProduitServices produitServices;

    @Autowired
    public ProduitAdminController(ProduitServices produitServices) {
        this.produitServices = produitServices;
    }


    @RequestMapping("toAjouter")
    public String ajouterProduit(Model model) {
        model.addAttribute("newProduit", new Produit());
        System.out.println("model = [" + model + "]");
        return "ajouter";
    }

    @PostMapping("ajouter")
    public String ajouterProduit(@Valid Produit p, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newProduit", p);
            return "redirect:toAjouter";
        }
        model.addAttribute("produit", p);
        produitServices.save(p);
        return "confirmation";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        produitServices.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("editprod", produitServices.findbyId(id));
        return "edit";
    }

    @RequestMapping("/chprod")
    public String chprod(@Valid Produit p, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editprod", p);
            return "redirect:edit";
        }
        model.addAttribute("produit", p);
        produitServices.save(p);
        return "redirect:/";
    }

}
