/**
 * Module descriptor for the Phone Store Management System.
 *
 * This module defines dependencies on JavaFX components and configures
 * reflection access for FXML controllers and JavaFX property binding.
 */
module com.example.miniproject4 {
    // JavaFX dependencies
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Optional: Add if you use JavaFX web view
    // requires javafx.web;

    // Optional: Add if you use JavaFX media
    // requires javafx.media;

    // Optional: Add for database connectivity
    // requires java.sql;

    // Optional: Add for JSON processing
    // requires com.google.gson;
    // requires com.fasterxml.jackson.databind;

    // Export main application package
    exports com.example.miniproject4;

    // Export controller package (for potential extension/reuse)
    exports com.example.miniproject4.controllers;

    // Export model package (for potential extension/reuse)
    exports com.example.miniproject4.models;

    // Open main package to javafx.fxml for FXML loading and dependency injection
    opens com.example.miniproject4 to javafx.fxml;

    // Open controllers package to javafx.fxml for @FXML annotation processing
    opens com.example.miniproject4.controllers to javafx.fxml;

    // Open models package to javafx.base and javafx.fxml for JavaFX property reflection and FXML binding
    // This is required for PropertyValueFactory, data binding, and FXML serialization
    opens com.example.miniproject4.models to javafx.base, javafx.fxml;
}