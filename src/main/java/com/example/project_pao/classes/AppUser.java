package com.example.project_pao.classes;

import java.util.ArrayList;
import java.util.List;

public class AppUser {
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Project> projects= new ArrayList<Project>();
    private List<Workspace> workspaces= new ArrayList<Workspace>();
    private List<Workspace> invitations= new ArrayList<Workspace>();
    private List<Workspace> sentinvitations= new ArrayList<Workspace>();

    //constructor
    public AppUser(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;

        this.email = email;
    }

    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;

        this.email = email;
    }

    public AppUser() {

    }

    //getters
public int getId() {
    return id;
}

public String getUsername() {
    return username;
}

public String getPassword() {
    return password;
}

//get porjects
public List<Project> getProjects() {
    return projects;
}

public String getEmail() {
    return email;
}

//setters


public void setId(int id) {
    this.id = id;
}

public void setUsername(String username) {
    this.username = username;
}

public void setPassword(String password) {
    this.password = password;


}

public void setEmail(String email) {
    this.email = email;
}

    //encrypt password with xor and return string
    public static String encryptPassword(String inputString)
    {
        // Define XOR key
        // Any character value will work
        char xorKey = 'P';

        // Define String to store encrypted/decrypted String
        String outputString = "";

        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every character in string
        for (int i = 0; i < len; i++)
        {
            outputString = outputString +
                    Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }


        return outputString;
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public  void addWorkspace(Workspace workspace){
        workspaces.add(workspace);
    }

}
