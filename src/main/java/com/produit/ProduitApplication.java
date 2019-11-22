package com.produit;

import com.produit.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProduitApplication implements CommandLineRunner {
    private final UserServices userServices;

    @Autowired
    public ProduitApplication(UserServices userServices) {
        this.userServices = userServices;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProduitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userServices.deleteAll();
        userServices.createUser("u1", "u1", "USER");
        userServices.createUser("u2", "u2", "ADMIN");

    }
}
