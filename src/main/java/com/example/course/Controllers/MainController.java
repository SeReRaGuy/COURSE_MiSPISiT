package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.ProgramStart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainController {
    public MainController() throws SQLException {}
    @FXML
    private Button openTableB;

    @FXML
    private void openTable() throws IOException {
        Stage stage = (Stage) openTableB.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-courses.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
