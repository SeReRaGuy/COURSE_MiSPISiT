package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.Departments;
import com.example.course.Models.Employees;
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

public class TableOfDepartments {
    public TableOfDepartments() throws SQLException {}
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
    private TextField nameField;
    @FXML
    private TextField headField;
    @FXML
    private TableView<Departments> tableDepartments;
    @FXML
    private TableColumn<Departments, Integer> idColumn;
    @FXML
    private TableColumn<Departments, String> nameColumn;
    @FXML
    private TableColumn<Departments, Integer> headColumn;


    @FXML
    private TableView<Employees> tableEmployees;
    @FXML
    private TableColumn<Employees, Integer> idColumnE;
    @FXML
    private TableColumn<Employees, String> firstNameColumn;
    @FXML
    private TableColumn<Employees, String> lastNameColumn;
    @FXML
    private TableColumn<Employees, String> middleNameColumn;
    @FXML
    private TableColumn<Employees, Integer> positionColumn;
    @FXML
    private TableColumn<Employees, Date> hireDateColumn;
    @FXML
    private TableColumn<Employees, String> phoneNumberColumn;
    @FXML
    private TableColumn<Employees, String> addressColumn;
    @FXML
    private TableColumn<Employees, Integer> statusColumn;
    @FXML
    private TableColumn<Employees, Integer> salaryColumn;
    @FXML
    private void initialize() throws SQLException {
        LoadTableDepartments();
    }
    @FXML
    private void LoadTableDepartments() throws SQLException {
        ObservableList<Departments> List = FXCollections.observableArrayList();
        ObservableList<Employees> ListE = FXCollections.observableArrayList();
        String sqlSelect = "SELECT * FROM departments";
        String sqlSelectEmployee = "SELECT * FROM employees";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
        PreparedStatement preparedStatementE = connect.prepareStatement(sqlSelectEmployee);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        headColumn.setCellValueFactory(new PropertyValueFactory<>("head"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Departments(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getInt(3)));
        }
        tableDepartments.setItems(List);

        idColumnE.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
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
        String addRecord = "INSERT INTO departments VALUES (?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addRecord);
        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, nameField.getText());
        preparedStatement.setInt(3, Integer.parseInt(headField.getText()));
        preparedStatement.executeUpdate();
        LoadTableDepartments();
    }
    @FXML
    private void delete() throws SQLException {
        String deleteRecord = "DELETE FROM departments WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableDepartments.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableDepartments();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE departments SET id = ?, name = ?, head = ? WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(editRecord);
        Departments departments = tableDepartments.getSelectionModel().getSelectedItem();
        int where = departments.getId();

        departments.setId(Integer.parseInt(idField.getText()));
        departments.setName(nameField.getText());
        departments.setHead(Integer.parseInt(headField.getText()));

        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, nameField.getText());
        preparedStatement.setInt(3, Integer.parseInt(headField.getText()));
        preparedStatement.setInt(4, where);
        preparedStatement.executeUpdate();
        LoadTableDepartments();
    }
}
