package com.example.course.Controllers;

import com.example.course.ProgramStart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button tablePositions;
    @FXML
    private Button tableStatuses;
    @FXML
    private Button tableEmployees;
    @FXML
    private Button tableDepartments;
    @FXML
    private Button tableEducation;
    @FXML
    private Button tableCourses;
    @FXML
    private Button tableResearch;
    @FXML
    private Button exit;
    @FXML
    private void openTablePositions() throws IOException {
        Stage stage = (Stage) tablePositions.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-positions.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void openTableStatuses() throws IOException {
        Stage stage = (Stage) tableStatuses.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-statuses.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void openTableEmployees() throws IOException {
        Stage stage = (Stage) tableEmployees.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-employees.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void openTableDepartments() throws IOException {
        Stage stage = (Stage) tableDepartments.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-departments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void openTableEducation() throws IOException {
        Stage stage = (Stage) tableEducation.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-education.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void openTableCourses() throws IOException {
        Stage stage = (Stage) tableCourses.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-courses.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void openTableResearch() throws IOException {
        Stage stage = (Stage) tableResearch.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("table-of-research.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void doExit(){
        System.exit(0);
    }
}
