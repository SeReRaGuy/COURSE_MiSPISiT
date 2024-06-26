package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.*;
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
import java.sql.*;

public class TableOfCourses {
    public TableOfCourses() throws SQLException {}
    private final Connection connect = ConnectionSQL.getInstance();
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
    private TableView<Departments> tableDepartments;
    @FXML
    private TableColumn<Departments, Integer> idColumnD;
    @FXML
    private TableColumn<Departments, String> nameColumnD;
    @FXML
    private TableColumn<Departments, Integer> headColumnD;
    @FXML
    private TableView<Employees> tableEmployees;
    @FXML
    private TableColumn<Employees, Integer> idColumnE;
    @FXML
    private TableColumn<Employees, String> firstNameColumnE;
    @FXML
    private TableColumn<Employees, String> lastNameColumnE;
    @FXML
    private TableColumn<Employees, String> middleNameColumnE;
    @FXML
    private TableColumn<Employees, Integer> positionColumnE;
    @FXML
    private TableColumn<Employees, Date> hireDateColumnE;
    @FXML
    private TableColumn<Employees, String> phoneNumberColumnE;
    @FXML
    private TableColumn<Employees, String> addressColumnE;
    @FXML
    private TableColumn<Employees, Integer> statusColumnE;
    @FXML
    private TableColumn<Employees, Integer> salaryColumnE;
    @FXML
    private void initialize() throws SQLException {
        LoadTableCourses();
    }
    @FXML
    private void LoadTableCourses() throws SQLException {
        ObservableList<Courses> List = FXCollections.observableArrayList();
        ObservableList<Departments> ListD = FXCollections.observableArrayList();
        ObservableList<Employees> ListE = FXCollections.observableArrayList();
        String sqlSelectCourses = "SELECT * FROM courses";
        String sqlSelectD = "SELECT * FROM departments";
        String sqlSelectE = "SELECT * FROM employees";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectCourses);
        PreparedStatement preparedStatementD = connect.prepareStatement(sqlSelectD);
        PreparedStatement preparedStatementE = connect.prepareStatement(sqlSelectE);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Courses(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getInt(4)));
        }
        tableCourses.setItems(List);

        idColumnD.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnD.setCellValueFactory(new PropertyValueFactory<>("name"));
        headColumnD.setCellValueFactory(new PropertyValueFactory<>("head"));
        ResultSet resultSetD = preparedStatementD.executeQuery();
        while (resultSetD.next()) {
            ListD.add(new Departments(resultSetD.getInt(1), resultSetD.getString(2),
                    resultSetD.getInt(3)));
        }
        tableDepartments.setItems(ListD);

        idColumnE.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumnE.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumnE.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleNameColumnE.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        positionColumnE.setCellValueFactory(new PropertyValueFactory<>("position"));
        hireDateColumnE.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        phoneNumberColumnE.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumnE.setCellValueFactory(new PropertyValueFactory<>("address"));
        statusColumnE.setCellValueFactory(new PropertyValueFactory<>("status"));
        salaryColumnE.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ResultSet resultSetE = preparedStatementE.executeQuery();
        while (resultSetE.next()) {
            ListE.add(new Employees(resultSetE.getInt(1), resultSetE.getString(2),
                    resultSetE.getString(3), resultSetE.getString(4),
                    resultSetE.getInt(5), resultSetE.getDate(6).toLocalDate(),
                    resultSetE.getString(7), resultSetE.getString(8),
                    resultSetE.getInt(9),resultSetE.getInt(10)));
        }
        tableEmployees.setItems(ListE);
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
        String deleteRecord = "DELETE FROM courses WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableCourses.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableCourses();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE courses SET id = ?, course_name = ?, department = ?, instructor = ?  WHERE id = ?";
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
