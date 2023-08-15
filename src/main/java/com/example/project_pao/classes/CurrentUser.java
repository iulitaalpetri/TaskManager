package com.example.project_pao.classes;

public class CurrentUser {
    public static AppUser loggedUser;

    public static void setLoggedUser(AppUser user){
        loggedUser = user;
    }

    public static AppUser getLoggedUser(){
        return loggedUser;
    }
}
