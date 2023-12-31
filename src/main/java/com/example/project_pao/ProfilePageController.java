package com.example.project_pao;

import com.example.project_pao.classes.CurrentUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController implements Initializable {

    @FXML
    private Button testbutton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;

    @FXML
    private Button ProfileLogoutButton;

    @FXML
    private Button seeAllProjectsButton;

    @FXML
    private Button seeAllWorkspacesButton;

    //set the username and email labels automatically when the profile page is onloaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(CurrentUser.getLoggedUser().getUsername());
        emailLabel.setText(CurrentUser.getLoggedUser().getEmail());
    }


    //logout button
    public void ProfileLogoutButtonOnAction(ActionEvent actionEvent) throws IOException {
        //ProfileLogoutButton.getScene().getWindow().hide();
        CurrentUser.setLoggedUser(null);
        //redirect to login page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();



    }

    //see all projects button
    public void seeAllProjectsButtonOnClick(ActionEvent actionEvent) throws IOException {
        //seeAllProjectsButton.getScene().getWindow().hide();
        //redirect to see all projects page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("allProjects.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        Stage stage = new Stage();
        stage.setTitle("See all projects");
        stage.setScene(scene);
        stage.show();
    }

    //see all workspaces button
    public void seeAllWorkspacesButtonOnClick(ActionEvent actionEvent) throws IOException {
        //seeAllWorkspacesButton.getScene().getWindow().hide();
        //redirect to see all workspaces page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("allWorkspaces.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        Stage stage = new Stage();
        stage.setTitle("See all workspaces");
        stage.setScene(scene);
        stage.show();
    }



}
