package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.Courses;
import com.example.course.Models.Departments;
import com.example.course.Models.Employees;
import com.example.course.Models.Research;
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

public class TableOfResearch {
    public TableOfResearch() throws SQLException {}
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
    private TextField departmentField;
    @FXML
    private TextField projectNameField;
    @FXML
    private TextField leadEmployeeField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;
    @FXML
    private TextField fundingAmountField;
    @FXML
    private TableView<Research> tableResearch;
    @FXML
    private TableColumn<Research, Integer> idColumn;
    @FXML
    private TableColumn<Research, String> departmentColumn;
    @FXML
    private TableColumn<Research, Integer> projectNameColumn;
    @FXML
    private TableColumn<Research, Integer> leadEmployeeColumn;
    @FXML
    private TableColumn<Research, Integer> startDateColumn;
    @FXML
    private TableColumn<Research, Integer> endDateColumn;
    @FXML
    private TableColumn<Research, Integer> fundingAmountColumn;
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
        ObservableList<Research> List = FXCollections.observableArrayList();
        ObservableList<Departments> ListD = FXCollections.observableArrayList();
        ObservableList<Employees> ListE = FXCollections.observableArrayList();
        String sqlSelectCourses = "SELECT * FROM research";
        String sqlSelectD = "SELECT * FROM departments";
        String sqlSelectE = "SELECT * FROM employees";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectCourses);
        PreparedStatement preparedStatementD = connect.prepareStatement(sqlSelectD);
        PreparedStatement preparedStatementE = connect.prepareStatement(sqlSelectE);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        leadEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("leadEmployee"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        fundingAmountColumn.setCellValueFactory(new PropertyValueFactory<>("fundingAmount"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Research(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getString(3), resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(), resultSet.getDate(6).toLocalDate(),
                    resultSet.getInt(7)));
        }
        tableResearch.setItems(List);

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
        String addRecord = "INSERT INTO research VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addRecord);
        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setInt(2, Integer.parseInt(departmentField.getText()));
        preparedStatement.setString(3, projectNameField.getText());
        preparedStatement.setInt(4, Integer.parseInt(leadEmployeeField.getText()));
        preparedStatement.setDate(5, Date.valueOf(startDateField.getValue()));
        preparedStatement.setDate(6, Date.valueOf(endDateField.getValue()));
        preparedStatement.setInt(7, Integer.parseInt(fundingAmountField.getText()));
        preparedStatement.executeUpdate();
        LoadTableCourses();
    }
    @FXML
    private void delete() throws SQLException {
        String deleteRecord = "DELETE FROM research WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableResearch.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableCourses();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE research SET id = ?, department = ?, project_name = ?, lead_employee = ?," +
                "start_date = ?, end_date = ?, funding_amount = ? WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(editRecord);
        Research research = tableResearch.getSelectionModel().getSelectedItem();
        int where = research.getId();

        research.setId(Integer.parseInt(idField.getText()));
        research.setDepartment(Integer.parseInt(departmentField.getText()));
        research.setProjectName(projectNameField.getText());
        research.setLeadEmployee(Integer.parseInt(leadEmployeeField.getText()));
        research.setStartDate(startDateField.getValue());
        research.setEndDate(endDateField.getValue());
        research.setFundingAmount(Integer.parseInt(fundingAmountField.getText()));

        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setInt(2, Integer.parseInt(departmentField.getText()));
        preparedStatement.setString(3, projectNameField.getText());
        preparedStatement.setInt(4, Integer.parseInt(leadEmployeeField.getText()));
        preparedStatement.setDate(5, Date.valueOf(startDateField.getValue()));
        preparedStatement.setDate(6, Date.valueOf(endDateField.getValue()));
        preparedStatement.setInt(7, Integer.parseInt(fundingAmountField.getText()));
        preparedStatement.setInt(8, where);
        preparedStatement.executeUpdate();
        LoadTableCourses();
    }
}
