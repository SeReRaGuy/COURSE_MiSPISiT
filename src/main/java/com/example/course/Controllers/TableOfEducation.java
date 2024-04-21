package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.Departments;
import com.example.course.Models.Education;
import com.example.course.Models.Employees;
import com.example.course.ProgramStart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TableOfEducation {
    public TableOfEducation() throws SQLException {}
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
    private TextField employeeField;
    @FXML
    private TextField degreeField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField specializationField;
    @FXML
    private TextField yearOfGraduationField;
    @FXML
    private TableView<Education> tableEducation;
    @FXML
    private TableColumn<Education, Integer> idColumn;
    @FXML
    private TableColumn<Education, Integer> employeeColumn;
    @FXML
    private TableColumn<Education, String> degreeColumn;
    @FXML
    private TableColumn<Education, String> institutionColumn;
    @FXML
    private TableColumn<Education, String> specializationColumn;
    @FXML
    private TableColumn<Education, Integer> yearOfGraduationColumn;


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
        LoadTableEducation();
    }
    @FXML
    private void LoadTableEducation() throws SQLException {
        ObservableList<Education> List = FXCollections.observableArrayList();
        ObservableList<Employees> ListE = FXCollections.observableArrayList();
        String sqlSelect = "SELECT * FROM education";
        String sqlSelectEmployee = "SELECT * FROM employees";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
        PreparedStatement preparedStatementE = connect.prepareStatement(sqlSelectEmployee);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        institutionColumn.setCellValueFactory(new PropertyValueFactory<>("institution"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        yearOfGraduationColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfGraduation"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Education(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getInt(6)));
        }
        tableEducation.setItems(List);

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
        String addRecord = "INSERT INTO education VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addRecord);
        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setInt(2, Integer.parseInt(employeeField.getText()));
        preparedStatement.setString(3, degreeField.getText());
        preparedStatement.setString(4, institutionField.getText());
        preparedStatement.setString(5, specializationField.getText());
        preparedStatement.setInt(6, Integer.parseInt(yearOfGraduationField.getText()));
        preparedStatement.executeUpdate();
        LoadTableEducation();
    }
    @FXML
    private void delete() throws SQLException {
        String deleteRecord = "DELETE FROM education WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableEducation.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableEducation();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE education SET id = ?, employee = ?, degree = ?, " +
                "institution = ?, specialization = ?, year_of_graduation = ? WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(editRecord);
        Education education = tableEducation.getSelectionModel().getSelectedItem();
        int where = education.getId();

        education.setId(Integer.parseInt(idField.getText()));
        education.setEmployee(Integer.parseInt(employeeField.getText()));
        education.setDegree(degreeField.getText());
        education.setInstitution(institutionField.getText());
        education.setSpecialization(specializationField.getText());
        education.setYearOfGraduation(Integer.parseInt(yearOfGraduationField.getText()));

        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setInt(2, Integer.parseInt(employeeField.getText()));
        preparedStatement.setString(3, degreeField.getText());
        preparedStatement.setString(4, institutionField.getText());
        preparedStatement.setString(5, specializationField.getText());
        preparedStatement.setInt(6, Integer.parseInt(yearOfGraduationField.getText()));
        preparedStatement.setInt(7, where);
        preparedStatement.executeUpdate();
        LoadTableEducation();
    }
}
