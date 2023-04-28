package com.example.project_pao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Authentification {
    private static boolean isLogged = false;




    // check if the username is in the database
    public static boolean checkUsername(String input_username){
        for(AppUser user: DbContext.getUsers()){
            if(user.getUsername().equals(input_username)){
                return true;
            }
        }
        return false;
    }

    // check for a given username and password if the password is correct
    public static boolean checkPassword(String input_username,String input_password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        for(AppUser user: DbContext.getUsers()){
            if(user.getUsername().equals(input_username)){
                if(user.getPassword().equals(input_password)){
                    return true;
                }
            }
        }
        return false;
    }

    //get user by username
    public static AppUser getUserByUsername(String input_username){
        for(AppUser user: DbContext.getUsers()){
            if(user.getUsername().equals(input_username)){
                return user;
            }
        }
        return null;
    }

    public static AppUser returnUser(String input_username,String input_password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        for(AppUser user: DbContext.getUsers()){
            if(user.getUsername().equals(input_username)){
                if(user.getPassword().equals(input_password)){
                    return user;
                }
            }
        }
        return null;
    }


    //register
    public static AppUser register() throws Exception {
        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        String input_username = scanner.nextLine();
        System.out.println("Enter password:");
        Scanner scanner2 = new Scanner(System.in);
        String input_password = scanner2.nextLine();
        System.out.println("Enter email:");
        Scanner scanner3 = new Scanner(System.in);
        String input_email = scanner3.nextLine();
        if(AppUser.checkUsernameExists(input_username)){
            throw new Exception("Username already exists");
        }else{
            if(AppUser.checkPassword(input_password)){
                if(AppUser.checkEmail(input_email)){
                    AppUser user = new AppUser(input_username, input_password, input_email);
                    DbContext.addUser(user);
                    System.out.println("Registration successful");
                    return user;
                }else{
                    throw new Exception("Email is not correct");
                }
            }else{
                throw new Exception("Password is not correct");
            }
        }
    }





    //login
    public static AppUser login() throws Exception {
        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        String input_username = scanner.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Enter password:");
        String input_password = scanner2.nextLine();
        if(checkUsername(input_username)){
            if(checkPassword(input_username, input_password)){
                System.out.println("Login successful");
                isLogged = true;
                return returnUser(input_username, input_password);
            }else{
                throw new Exception("Password is not correct");
            }
        }else{
            throw new Exception("Username is not correct");
        }
    }

    //la delogare se reseteaza isLogged
    public static void logout(){
        isLogged= false;
    }

}
