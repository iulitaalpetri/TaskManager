package com.example.project_pao.classes;

public class Task {
    private int id;
    private String name;
    private String description;
    private Project project;
    private Boolean checked;
    private AppUser id_appuser;


    //constructor
    public Task(int id, String name, String description, Project project, Boolean checked, AppUser idappuser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.project = project;
        this.checked = checked;
        this.id_appuser= idappuser;
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

    public Project getProject() {
        return project;
    }

    public Boolean getChecked() {
        return checked;
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

    public void setProject(Project project) {
        this.project = project;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }




}
