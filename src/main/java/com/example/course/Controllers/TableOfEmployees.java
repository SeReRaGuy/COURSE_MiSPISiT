package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.Employees;
import com.example.course.Models.Positions;
import com.example.course.Models.Statuses;
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

public class TableOfEmployees {
    public TableOfEmployees() throws SQLException {}
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
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private TextField positionField;
    @FXML
    private DatePicker hireDateField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField salaryField;
    @FXML
    private TableView<Employees> tableEmployees;
    @FXML
    private TableColumn<Employees, Integer> idColumn;
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
    private TableView<Statuses> tableStatuses;
    @FXML
    private TableColumn<Statuses, Integer> idColumnS;
    @FXML
    private TableColumn<Statuses, String> nameColumnS;
    @FXML
    private TableView<Positions> tablePositions;
    @FXML
    private TableColumn<Positions, Integer> idColumnP;
    @FXML
    private TableColumn<Positions, String> nameColumnP;
    @FXML
    private void initialize() throws SQLException {
        LoadTableEmployees();
    }
    @FXML
    private void LoadTableEmployees() throws SQLException {
        ObservableList<Employees> List = FXCollections.observableArrayList();
        ObservableList<Statuses> ListS = FXCollections.observableArrayList();
        ObservableList<Positions> ListP = FXCollections.observableArrayList();
        String sqlSelectEmployee = "SELECT * FROM employees";
        String sqlSelectStatuses = "SELECT * FROM statuses";
        String sqlSelectPositions = "SELECT * FROM positions";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectEmployee);
        PreparedStatement preparedStatementS = connect.prepareStatement(sqlSelectStatuses);
        PreparedStatement preparedStatementP = connect.prepareStatement(sqlSelectPositions);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Employees(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getInt(5), resultSet.getDate(6).toLocalDate(),
                    resultSet.getString(7), resultSet.getString(8),
                    resultSet.getInt(9),resultSet.getInt(10)));
        }
        tableEmployees.setItems(List);

        idColumnS.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnS.setCellValueFactory(new PropertyValueFactory<>("name"));
        ResultSet resultSetS = preparedStatementS.executeQuery();
        while (resultSetS.next()) {
            ListS.add(new Statuses(resultSetS.getInt(1), resultSetS.getString(2)));
        }
        tableStatuses.setItems(ListS);

        idColumnP.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnP.setCellValueFactory(new PropertyValueFactory<>("name"));
        ResultSet resultSetP = preparedStatementP.executeQuery();
        while (resultSetP.next()) {
            ListP.add(new Positions(resultSetP.getInt(1), resultSetP.getString(2)));
        }
        tablePositions.setItems(ListP);
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
        String addRecord = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addRecord);
        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, firstNameField.getText());
        preparedStatement.setString(3, lastNameField.getText());
        preparedStatement.setString(4, middleNameField.getText());
        preparedStatement.setInt(5, Integer.parseInt(positionField.getText()));
        preparedStatement.setDate(6, Date.valueOf(hireDateField.getValue()));
        preparedStatement.setString(7, phoneNumberField.getText());
        preparedStatement.setString(8, addressField.getText());
        preparedStatement.setInt(9, Integer.parseInt(statusField.getText()));
        preparedStatement.setInt(10, Integer.parseInt(salaryField.getText()));
        preparedStatement.executeUpdate();
        LoadTableEmployees();
    }
    @FXML
    private void delete() throws SQLException {
        String deleteRecord = "DELETE FROM employees WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableEmployees.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableEmployees();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE employees SET id = ?, first_name = ?, last_name = ?, middle_name = ?," +
                "position = ?, hire_date = ?, phone_number = ?," +
                "address = ?, status = ?, salary = ? WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(editRecord);
        Employees employees = tableEmployees.getSelectionModel().getSelectedItem();
        int where = employees.getId();

        employees.setId(Integer.parseInt(idField.getText()));
        employees.setFirstName(firstNameField.getText());
        employees.setLastName(lastNameField.getText());
        employees.setMiddleName(middleNameField.getText());
        employees.setPosition(Integer.parseInt(positionField.getText()));
        employees.setHireDate(hireDateField.getValue());
        employees.setPhoneNumber(phoneNumberField.getText());
        employees.setAddress(addressField.getText());
        employees.setStatus(Integer.parseInt(statusField.getText()));
        employees.setSalary(Integer.parseInt(salaryField.getText()));

        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, firstNameField.getText());
        preparedStatement.setString(3, lastNameField.getText());
        preparedStatement.setString(4, middleNameField.getText());
        preparedStatement.setInt(5, Integer.parseInt(positionField.getText()));
        preparedStatement.setDate(6, Date.valueOf(hireDateField.getValue()));
        preparedStatement.setString(7, phoneNumberField.getText());
        preparedStatement.setString(8, addressField.getText());
        preparedStatement.setInt(9, Integer.parseInt(statusField.getText()));
        preparedStatement.setInt(10, Integer.parseInt(salaryField.getText()));
        preparedStatement.setInt(11, where);
        preparedStatement.executeUpdate();
        LoadTableEmployees();
    }
}
