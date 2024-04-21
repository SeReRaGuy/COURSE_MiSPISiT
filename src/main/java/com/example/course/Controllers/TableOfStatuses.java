package com.example.course.Controllers;

import com.example.course.ConnectionSQL;
import com.example.course.Models.Positions;
import com.example.course.Models.Statuses;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableOfStatuses {
    public TableOfStatuses() throws SQLException {}
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
    private TableView<Statuses> tableStatuses;
    @FXML
    private TableColumn<Statuses, Integer> idColumn;
    @FXML
    private TableColumn<Statuses, String> nameColumn;
    @FXML
    private void initialize() throws SQLException {
        LoadTableStatuses();
    }
    @FXML
    private void LoadTableStatuses() throws SQLException {
        ObservableList<Statuses> List = FXCollections.observableArrayList();
        String sqlSelect = "SELECT * FROM statuses";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List.add(new Statuses(resultSet.getInt(1), resultSet.getString(2)));
        }
        tableStatuses.setItems(List);
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
        String addRecord = "INSERT INTO statuses VALUES (?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addRecord);
        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, nameField.getText());
        preparedStatement.executeUpdate();
        LoadTableStatuses();
    }
    @FXML
    private void delete() throws SQLException {
        String deleteRecord = "DELETE FROM statuses WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deleteRecord);
        preparedStatement.setInt(1, tableStatuses.getSelectionModel().getSelectedItem().getId());
        preparedStatement.executeUpdate();
        LoadTableStatuses();

    }
    @FXML
    private void change() throws SQLException {
        String editRecord = "UPDATE statuses SET id = ?, name = ? WHERE id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(editRecord);
        Statuses statuses = tableStatuses.getSelectionModel().getSelectedItem();
        int where = statuses.getId();

        statuses.setId(Integer.parseInt(idField.getText()));
        statuses.setName(nameField.getText());

        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
        preparedStatement.setString(2, nameField.getText());
        preparedStatement.setInt(3, where);
        preparedStatement.executeUpdate();
        LoadTableStatuses();
    }
}
