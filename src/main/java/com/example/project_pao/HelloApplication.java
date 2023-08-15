package com.example.project_pao;

import com.example.project_pao.db.DbFunctions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        DbFunctions db=new DbFunctions();
        Connection conn=db.connect_to_db("Task Manager","postgres","anarepere");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource( "login.fxml" ));
        Scene scene = new Scene(fxmlLoader.load(), 555, 425);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}