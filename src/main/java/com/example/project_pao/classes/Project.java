package com.example.project_pao.classes;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private String description;
    private String deadline;//"dd-mm-yyyy"
    private boolean isworkspace;
    private List<Task> tasks=new ArrayList<Task>();



    //constructor
    public Project(int id, String name, String description, String deadline, boolean isworkspace, int owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.isworkspace = isworkspace;

    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setIsworkspace(boolean isworkspace) {
        this.isworkspace = isworkspace;
    }





    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean getIsworkspace() {
        return isworkspace;
    }








}
