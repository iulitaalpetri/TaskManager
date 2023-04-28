package com.example.project_pao;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfilePageController {

    @FXML
    private Button testbutton;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;

    public void testbuttonOnAction() {
//        usernameLabel.setText(current_user.this_user.getUsername());
//        emailLabel.setText(current_user.this_user.getEmail());
        usernameLabel.setText(current_user.this_user.getUsername());
        emailLabel.setText(current_user.this_user.getEmail());
    }
}
