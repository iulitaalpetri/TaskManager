module com.example.project_pao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_pao to javafx.fxml;
    exports com.example.project_pao;

}