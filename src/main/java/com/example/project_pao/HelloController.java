package com.example.project_pao;

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

public class HelloController {
    AppUser user = null;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField UsernameTextField;

    @FXML
    private Button exitButton;



    @FXML
    private PasswordField PasswordTextField;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onLoginButtonClick() throws Exception {
        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();
        user = Authentification.login();

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