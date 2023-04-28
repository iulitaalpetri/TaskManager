package com.example.project_pao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Workspace {
    private String name;
    private AppUser admin; // editor, sends invitations
    private List<AppUser> users= new ArrayList<>(); //viewers, isi pot alege taskurile
    private Project project;
    private List<AppUser> invitations= new ArrayList<>(); //invitations  join a workspace

    public Workspace(Project project, AppUser admin) {
        this.name= project.getName();
        this.project = project;
        this.admin = admin;
    }


    @java.lang.Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return name == workspace.name && Objects.equals(admin, workspace.admin) && Objects.equals(users, workspace.users) && Objects.equals(project, workspace.project);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(name, admin, users, project);
    }

    //send invitation
    public void sendInvitation(AppUser user){
        user.setInvitations(this);
    }
    //add user
    public void addUser(AppUser user){
        //if the user has accepted the invitation, he is added to the workspace
        if(user.getAcceptedInvitations().contains(this)){
            users.add(user);

        }
    }




    //remove user
    public void removeUser(AppUser user){
        users.remove(user);
    }

    //get users
    public List<AppUser> getUsers(){
        return users;
    }

    public void chooseTask(Task t){
        t.setInCharge(this.admin.getUsername());
    }

    public Project getProject() {
        return project;
    }

    public void removeInvitation(AppUser user) {
        this.invitations.remove(user);
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return project.getTasks();
    }


    //add workspace









}

