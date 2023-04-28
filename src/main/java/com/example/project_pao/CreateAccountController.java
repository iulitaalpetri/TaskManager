package com.example.project_pao;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController  {
    @FXML
    private TextField createusername;

    @FXML
    private TextField createemail;

    @FXML
    private PasswordField createpassword;

    @FXML
    private PasswordField createconfirmpassword;
    @FXML
    private Label createAccountMessage;
    @FXML
    private Label createEnterUsername;
    @FXML
    private Label createEnterEmail;
    @FXML
    private Label createEnterPassword;
    @FXML
    private Label createEnterConfirmPassword;
    @FXML
    private Label createPasswordNotMatch;



    public void CreateAccountOnAction(ActionEvent actionEvent) throws Exception {
        //verify if the fields are not empty
        if(createusername.getText().isBlank()) createEnterUsername.setText("Please enter a username");
        else createEnterUsername.setText("");

        if(createemail.getText().isBlank()) createEnterEmail.setText("Please enter an email");
        else createEnterEmail.setText("");

        if(createpassword.getText().isBlank()) createEnterPassword.setText("Please enter a password");
        else createEnterPassword.setText("");

        if(createconfirmpassword.getText().isBlank()) createEnterConfirmPassword.setText("Please confirm your password");
        else createEnterConfirmPassword.setText("");


        //verify if the passwords match
        if(!createpassword.getText().equals(createconfirmpassword.getText())) createPasswordNotMatch.setText("Passwords do not match");
        else createPasswordNotMatch.setText("");

        //verify if the username has length at least 3
        if(createusername.getText().length() < 3) createEnterUsername.setText("Username must have at least 3 characters");
        else createEnterUsername.setText("");

        //verify if the password has length at least 8
        if(createpassword.getText().length() < 8) createEnterPassword.setText("Password must have at least 8 characters");
        else createEnterPassword.setText("");

        //verify if the email is valid
        if(!createemail.getText().contains("@")) createEnterEmail.setText("Please enter a valid email");
        else createEnterEmail.setText("");


        if(createusername.getText().isBlank() || createemail.getText().isBlank() || createpassword.getText().isBlank() || createconfirmpassword.getText().isBlank() || !createpassword.getText().equals(createconfirmpassword.getText())) return;
        if(createusername.getText().length() < 3 || createpassword.getText().length() < 8 || !createemail.getText().contains("@")) return;
        current_user.this_user = new AppUser(createusername.getText(), createemail.getText(), createpassword.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }


    public void alreadyhaveaccountbuttonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
}
