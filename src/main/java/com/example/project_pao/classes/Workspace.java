package com.example.project_pao.classes;

import java.util.List;

public class Workspace {
    private int id;
    private String name;
    private AppUser idowner;
    private Project idproject;
    private List<AppUser> members;

    //constructor
    public Workspace(int id, String name, AppUser owner, Project project) {
        this.id = id;
        this.name = name;
        this.idowner = owner;
        this.idproject = project;

    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public List<AppUser> getMembers() {
        return members;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }





    public void setMembers(List<AppUser> members) {
        this.members = members;
    }


}
