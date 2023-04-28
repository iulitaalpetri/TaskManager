package com.example.project_pao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUser {
    private TaskBoard taskBoard;
    private int id;
    private String username;
    private String password;
    private String email;
    private Set<Workspace> invitations= new HashSet<>() ; //invitations  join a workspace
    private List<Workspace> acceptedInvitations= new ArrayList<>(); //workspaces to which the user has accepted the invitation
    private List<Workspace> workspaces= new ArrayList<>(); //workspaces where the user is admin

    //Validari
    // function to check if the password is correct
    public static boolean checkPassword(String password){
//        if(password.length()>=8){
//            return true;
//        }
//        return false;
        return true;
    }


    // function to check if the email is correct
    public static boolean checkEmail(String email){
        if(email.contains("@")){
            return true;
        }
        return false;
    }

    // function to check if the username is correct
    public boolean checkUsername(String username){
        if(username.length()>=3){
            return true;
        }
        return false;
    }

    //function that checks if the username already exists
    public static boolean checkUsernameExists(String username){
        for(AppUser user: DbContext.getUsers()){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }





    //constructor with validation
    public AppUser(String username, String password, String email) throws Exception {
        this.username = username;
        this.password = password;
        this.email = email;
        this.taskBoard =new TaskBoard();//compozitie
        DbContext.addUser(this);
        System.out.println("User created");


    }












    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //get accepted invitations
    public List<Workspace> getAcceptedInvitations() {
        return acceptedInvitations;
    }



    //set invitations
    public void setInvitations(Workspace workspace){
        this.invitations.add(workspace);
    }

    //accept an invitation to a workspace and add it to the list of accepted invitations
    public void acceptInvitation(Workspace workspace){
        this.acceptedInvitations.add(workspace);
        this.invitations.remove(workspace);

    }


    //decline an invitation to a workspace and remove it from the list of invitations
    public void declineInvitation(Workspace workspace){
        this.invitations.remove(workspace);
    }


    public boolean addedWorkspace(Workspace worksapace){
        if (worksapace.getUsers().contains(this)){
            return true;
        }
        return false;
    }

    public void chooseTask(Task t){
        t.setInCharge(this.username);
    }


    public TaskBoard TaskBoard() {
        return taskBoard;
    }


    public void addWorkspace(Workspace workspace) {
        this.workspaces.add(workspace);
    }

    public Workspace[] getInvitations() {
        return invitations.toArray(new Workspace[0]);
    }

    public Workspace getWorkspace(String name) {
        for (Workspace workspace : workspaces) {
            if (workspace.getName().equals(name)) {
                return workspace;
            }
        }
        return null;
    }

    public String getName() {
        return username;
    }

}
