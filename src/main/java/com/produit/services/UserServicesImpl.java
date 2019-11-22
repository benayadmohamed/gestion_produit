package com.produit.services;

import com.produit.models.AppUser;
import com.produit.models.Role;
import com.produit.repositories.RoleRepositories;
import com.produit.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private RoleRepositories roleRepositories;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser findbyEmail(String email) {
        return userRepositories.findByEmail(email);
    }

    public void createUser(String email, String password, String role) {
        Role role1 = roleRepositories.findByName(role);
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role1);

        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setRoles(roles);
        userRepositories.save(appUser);
    }

    @Override
    public void deleteAll() {
        userRepositories.deleteAll();
    }
}
