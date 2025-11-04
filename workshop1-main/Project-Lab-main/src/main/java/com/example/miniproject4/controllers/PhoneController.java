package com.example.miniproject4.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class PhoneController {
    @FXML
    private Button phonesBtn;

    @FXML
    void openPhonesManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/miniproject4/views/phones-view.fxml")
            );
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Phone Management");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showError("Unable to open Phone Management view", e.getMessage());
            e.printStackTrace(); // Log the stack trace for debugging
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}