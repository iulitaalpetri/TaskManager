package com.example.project_pao;

import java.util.ArrayList;
import java.util.List;

public class DbContext {
    private static List<AppUser> useri=new ArrayList<>();

    public static void addUser(AppUser user){
        useri.add(user);
    }
    public static List<AppUser> getUsers(){
        return useri;
    }

}
