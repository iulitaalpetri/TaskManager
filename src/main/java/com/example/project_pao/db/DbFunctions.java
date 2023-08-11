package com.example.project_pao.db;

import com.example.project_pao.classes.AppUser;
import com.example.project_pao.classes.Project;
import com.example.project_pao.classes.Task;

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
    static void AddUserDb(AppUser user) {
        Connection conn = null;
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "INSERT INTO " + "appuser" + " ( username, password, email) VALUES(?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
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
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    //check if the username and the password exist
    public static Boolean checkPasswordUsername(String username, String password){
        List<AppUser> users= getUsers();
        for(AppUser user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(AppUser.encryptPassword(password)));
                return true;
        }

        return false;
    }


    //return the user with the given username and password
    public static AppUser returnUser(String input_username, String input_password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        for( AppUser user: DbFunctions.getUsers()){
            if(user.getUsername().equals(input_username)){
                if(user.getPassword().equals(AppUser.encryptPassword(input_password))){
                    return user;
                }
            }
        }
        return null;
    }

    //get all info about a user
    public static void GetAllInfoUserDb(AppUser user) {
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
                user.setEmail(rs.getString("email"));
            }
            db.close_connection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                Project project = new Project(id, name, description, deadline, isworkspace, id_user);
                projects.add(project);
            }
            db.close_connection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }

    //get all workspaces of a user
    public static List<Project> getWorkspaces(AppUser user) {
        Connection conn = null;
        List<Project> projects = new ArrayList<>();
        try {
            com.example.project_pao.db.DbFunctions db = new com.example.project_pao.db.DbFunctions();
            conn = db.connect_to_db("Task Manager", "postgres", "anarepere");
            String query = "SELECT * FROM project WHERE id_user = ? and isworkspace= ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String deadline = rs.getString("deadline");
                Boolean isworkspace = rs.getBoolean("isworkspace");
                int id_user = rs.getInt("idappuser");
                Project project = new Project(id, name, description, deadline, isworkspace, id_user);
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











}
