package com.example.project_pao.db;

import com.example.project_pao.classes.AppUser;
import com.example.project_pao.classes.CurrentUser;
import com.example.project_pao.classes.Project;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Currency;
import java.util.List;

public class test {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<AppUser> lista= DbFunctions.getUsers();




        for(AppUser user: lista){
            System.out.println(user.getUsername());
        }
    }
}
