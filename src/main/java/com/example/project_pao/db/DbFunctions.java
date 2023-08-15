package com.example.project_pao.db;

import com.example.project_pao.classes.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions {

    // connect to database
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }


    //close connection
    public void close_connection(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    //send validation email
    static void sendValidationEmail(AppUser user) {
        // TODO
    }

    //add user to database
    public static void AddUserDb(AppUser user) {
        Connection conn = null;
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "INSERT INTO " + "appuser" + " ( username, password, email) VALUES(?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, AppUser.encryptPassword(user.getPassword()));
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();


           // db.close_connection(conn);
            System.out.println("User added successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    //get all users
    static List<AppUser> getUsers() {
        Connection conn = null;
        List<AppUser> users = new ArrayList<>();
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "SELECT * FROM appuser";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                AppUser user = new AppUser(id, username, password, email);
                users.add(user);
            }


        } catch (SQLException e) {
            System.out.println("no users found");
        }
        return users;

    }

    //check if the username and the password exist
    public static Boolean checkPasswordUsername(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<AppUser> users= getUsers();
        for(AppUser user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(AppUser.encryptPassword(password))){
                System.out.println("usernames"+ user.getUsername() + " " + username);
                System.out.println("passwords"+ user.getPassword() + " " + AppUser.encryptPassword(password));

                return true;
        }

    }

        return false;
    }




    //get all info about a user
    public static AppUser GetAllInfoUserDb(AppUser user) {
        Connection conn = null;
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "SELECT * FROM appuser WHERE username = ? AND password = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));

            }
            db.close_connection(conn);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    //get all personal projects of a user
    public static List<Project> getPersonalProjects(AppUser user) {
        Connection conn = null;
        List<Project> projects = new ArrayList<>();
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "SELECT * FROM project WHERE id_user = ? and isworkspace= ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String deadline = rs.getString("deadline");
                Boolean isworkspace = rs.getBoolean("isworkspace");
                int id_user = rs.getInt("idappuser");
                Project project = new Project(id, name, description, deadline, isworkspace);
                projects.add(project);
            }
            db.close_connection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }


    //check if the username already exists
    public static Boolean checkUsername(String username){
        List<AppUser> users= getUsers();
        for(AppUser user: users){
            if(user.getUsername().equals(username))
                return true;
        }

        return false;
    }

    //get user id by username
    public static int getUserId(String username){
        List<AppUser> lista= getUsers();

        for(AppUser a: lista){
            if (a.getUsername().equals(username))
                return a.getId();
        }
        return -1;
    }

    public static void login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        for(AppUser user: getUsers()){
            if(user.getUsername().equals(username) && user.getPassword().equals(AppUser.encryptPassword(password))){
                CurrentUser.setLoggedUser(user);
                return;
            }
        }
    }

    public static void logout(){
        CurrentUser.setLoggedUser(null);
    }


    //project

    //get all projects
    public static List<Project> getProjects() {
        Connection conn = null;
        List<Project> projects = new ArrayList<>();
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "SELECT * FROM project;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String deadline = rs.getString("deadline");
                Boolean isworkspace = rs.getBoolean("isworkspace");

                Project project = new Project(id, name, description, deadline, isworkspace);
                projects.add(project);
            }
            db.close_connection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }

    //get project by name and userid
    public static int getProjectId(String name, int iduser){
        List<Project> lista= getProjects();

        for(Project a: lista){
            if (a.getName().equals(name) && CurrentUser.loggedUser.getId()==iduser)
                return a.getId();
        }
        return -1;
    }

    //add project to database
    public static void addProject(Project project) {
        Connection conn = null;
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "INSERT INTO project(name, description, deadline,idappuser, isworkspace) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getDeadline());
            stmt.setInt(4, CurrentUser.loggedUser.getId());
            stmt.setBoolean(5, project.getIsworkspace());
            stmt.executeUpdate();
            project.setId(getProjectId(project.getName(), CurrentUser.loggedUser.getId()));
            CurrentUser.loggedUser.addProject(project);//adaugam proiectul in lista de proiecte a utilizatorului

            db.close_connection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //get all current user projects
    public static void getCurrentUserProjects(){
        Connection conn = null;
        List<Project> projects = new ArrayList<>();
        try{
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            int iduser= CurrentUser.loggedUser.getId();
            String query = "select * from project where idappuser = ? and isworkspace=?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, iduser);
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String deadline = rs.getString("deadline");
                Boolean isworkspace = rs.getBoolean("isworkspace");
                Project project = new Project(id, name, description, deadline, isworkspace);
                CurrentUser.loggedUser.addProject(project);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //get all workspaces of a user
    public static List<Workspace> getWorkspacesCurrentUser(){
        Connection conn = null;
        List<Workspace> workspaces = new ArrayList<>();
        try{
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            int iduser= CurrentUser.loggedUser.getId();
            String query = "select * from workspace where adminid = ? ;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, iduser);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int projectid = rs.getInt("projectid");
                for(Project p: CurrentUser.loggedUser.getProjects()){
                    if(p.getId()==projectid){
                        Workspace workspace = new Workspace(id, name,CurrentUser.loggedUser, p);
                        workspaces.add(workspace);
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workspaces;

    }

    //get workspace id by name and projectid
    public static int getWorkspaceId(String name, int idproject){
        List<Workspace> lista= getWorkspacesCurrentUser();

        for(Workspace a: lista){
            if (a.getName().equals(name) && a.getProject().getId()==idproject)
                return a.getId();
        }
        return -1;
    }

    //add workspace
    public static void addWorkspace(Project p){
        DbFunctions.addProject(p);
        Connection conn = null;
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "INSERT INTO workspace(name, adminid, projectid) VALUES(?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, p.getName());
            stmt.setInt(2, CurrentUser.loggedUser.getId());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
            int id= getWorkspaceId(p.getName(), p.getId());
            Workspace w = new Workspace(id, p.getName(), CurrentUser.loggedUser, p);
            CurrentUser.loggedUser.addWorkspace(w); //adaugam workspace-ul in lista de workspace-uri a utilizatorului

            db.close_connection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    //get all tasks of a project
//    public static List<Task> getTasksProject(Project p){
//        Connection conn = null;
//        List<Task> tasks = new ArrayList<>();
//        try{
//            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
//            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
//            int idproject= p.getId();
//            String query = "select * from task where projectid = ? ;";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setInt(1, idproject);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                String deadline = rs.getString("deadline");
//                Boolean isdone = rs.getBoolean("isdone");
//                Task task = new Task(id, name, description, deadline, isdone, p);
//                tasks.add(task);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return tasks;
//
//    }













}
