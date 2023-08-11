package com.example.project_pao.db;

import com.example.project_pao.classes.AppUser;

import java.util.List;

public class test {
    public static void main(String[] args) {
        List<AppUser> lista= DbFunctions.getUsers();

        for(AppUser a: lista)
            System.out.println(a.getUsername());

        DbFunctions.AddUserDb(new AppUser(1, "ana","ana", "ana@gmail.com"));


    }
}
