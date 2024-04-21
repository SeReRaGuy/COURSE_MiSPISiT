package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.Courses;
import com.example.course.ProgramStart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableOfCourses {
    public TableOfCourses() throws SQLException {}
    private Connection connect = ConnectionSQL.doConnect();
    @FXML
    private Button goToMainMenu;
    @FXML
    private Button doAdd;
    @FXML
    private Button doChange;
    @FXML
    private Button doDelete;
    @FXML
    private TextField idField;
    @FXML
    private TextField courseNameField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField instructorField;
    @FXML
    private TableView<Courses> tableCourses;
    @FXML
    private TableColumn<Courses, Integer> idColumn;
    @FXML
    private TableColumn<Courses, String> courseNameColumn;
    @FXML
    private TableColumn<Courses, Integer> departmentColumn;
    @FXML
    private TableColumn<Courses, Integer> instructorColumn;

    @FXML
    private void initialize() throws SQLException {
        LoadTableCourses();
    }
    @FXML
    private void LoadTableCourses() throws SQLException {
        ObservableList<Courses> List = FXCollections.observableArrayList();
        String sqlSelect = "SELECT * FROM courses";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Courses(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getInt(3),resultSet.getInt(4)));
        }
        tableCourses.setItems(List);
    }

    @FXML
    private void openMain() throws IOException {
        Stage stage = (Stage) goToMainMenu.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStart.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add() throws SQLException {
        String addRecord = "INSERT INTO courses VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addRecord);
        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, courseNameField.getText());
        preparedStatement.setInt(3, Integer.parseInt(departmentField.getText()));
        preparedStatement.setInt(4, Integer.parseInt(instructorField.getText()));
        preparedStatement.executeUpdate();
        LoadTableCourses();
    }
    @FXML
    private void delete() throws SQLException {
        String deleteRecord = "DELETE FROM medicines WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableCourses.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableCourses();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE medicines SET id = ?, course_name = ?, department = ?, instructor = ? WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(editRecord);
        Courses courses = tableCourses.getSelectionModel().getSelectedItem();
        int where = courses.getId();

        courses.setId(Integer.parseInt(idField.getText()));
        courses.setCourseName(courseNameField.getText());
        courses.setDepartment(Integer.parseInt(departmentField.getText()));
        courses.setInstructor(Integer.parseInt(instructorField.getText()));

        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, courseNameField.getText());
        preparedStatement.setInt(3, Integer.parseInt(departmentField.getText()));
        preparedStatement.setInt(4, Integer.parseInt(instructorField.getText()));
        preparedStatement.setInt(5, where);
        preparedStatement.executeUpdate();
        LoadTableCourses();
    }
}
