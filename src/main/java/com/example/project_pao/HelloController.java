package com.example.project_pao;

import com.example.project_pao.classes.CurrentUser;
import com.example.project_pao.db.DbFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.project_pao.db.DbFunctions.*;

public class HelloController {
    

    @FXML
    private Label welcomeText;
    @FXML
    private TextField UsernameTextField;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private Button exitButton;
    @FXML
    private Label LoginLabel;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onLoginButtonClick() throws Exception {
        LoginLabel.setText("");
        //verify if the fields are not empty
        if(UsernameTextField.getText().isBlank() || PasswordTextField.getText().isBlank())
        { LoginLabel.setText("Please enter a username and a passowrd");
            return;}
        else LoginLabel.setText("");


        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();

        if(DbFunctions.checkPasswordUsername(username,password)){

            LoginLabel.setText("Login successful!");
            com.example.project_pao.classes.AppUser user= returnUser(username,password);
            CurrentUser.loggedUser = user;
        }
        else LoginLabel.setText("Wrong username or password");



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile_page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 555, 425);
            Stage stage = new Stage();
            stage.setTitle("Profile Page");
            stage.setScene(scene);
            stage.show();


        }




    public void ExitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void CreateAnAccountButtononAction(ActionEvent actionEvent) throws IOException {
        //open create_account.fxml when the CreateAnAccount button is pressed without exit button
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create_account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}