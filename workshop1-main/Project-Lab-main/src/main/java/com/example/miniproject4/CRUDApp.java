package com.example.miniproject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Phone Store Management System.
 * This class initializes and launches the JavaFX application.
 */
public class CRUDApp extends Application {

    // Application constants
    private static final String APP_TITLE = "Phone Store Management System";
    private static final String FXML_PATH = "/com/example/miniproject4/views/dealer-view.fxml";
    private static final double WINDOW_WIDTH = 650.0;
    private static final double WINDOW_HEIGHT = 500.0;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = fxmlLoader.load();

            // Create scene
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Optional: Add CSS stylesheet
            // scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());

            // Configure stage
            primaryStage.setTitle(APP_TITLE);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Optional: Set application icon
            // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));

            // Optional: Handle window close request
            primaryStage.setOnCloseRequest(event -> {
                // Add any cleanup logic here if needed
                System.out.println("Application closing...");
            });

            // Show the stage
            primaryStage.show();

        } catch (IOException e) {
            showErrorDialog("Application Error",
                    "Failed to load the application interface",
                    e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showErrorDialog("Unexpected Error",
                    "An unexpected error occurred",
                    e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays an error dialog to the user.
     */
    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Application entry point.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}