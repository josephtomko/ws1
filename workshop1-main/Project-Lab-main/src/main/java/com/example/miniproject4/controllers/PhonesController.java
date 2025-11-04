package com.example.miniproject4.controllers;

import com.example.miniproject4.models.Phone;
import com.example.miniproject4.models.PhonesStore;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PhonesController {
    @FXML
    private TableView<Phone> phonesTable;
    @FXML
    private TableColumn<Phone, String> modelCol;
    @FXML
    private TableColumn<Phone, Integer> storageCol;
    @FXML
    private TableColumn<Phone, Double> priceCol;

    @FXML
    private TextField modelFld;
    @FXML
    private TextField storageFld;
    @FXML
    private TextField priceFld;

    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Text errorMsg;

    private final PhonesStore phoneStore = new PhonesStore();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadPhonesData();
        setupSelectionListener();
    }

    private void setupTableColumns() {
        modelCol.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        storageCol.setCellValueFactory(cellData -> cellData.getValue().storageProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
    }

    private void loadPhonesData() {
        ObservableList<Phone> phones = phoneStore.getPhonesList();
        phonesTable.setItems(phones);
    }

    private void setupSelectionListener() {
        phonesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFields(newValue);
            }
        });
    }

    private void populateFields(Phone phone) {
        modelFld.setText(phone.getModel());
        storageFld.setText(String.valueOf(phone.getStorage()));
        priceFld.setText(String.valueOf(phone.getPrice()));
    }

    @FXML
    void addPhone(ActionEvent event) {
        List<String> errors = validatePhoneData();
        if (!errors.isEmpty()) { showErrors(errors); return; }

        Phone phone = createPhoneFromFields();
        phoneStore.addPhone(phone);
        clearFields();
        showSuccess("Phone added successfully!");
    }

    @FXML
    void updatePhone(ActionEvent event) {
        Phone selectedPhone = phonesTable.getSelectionModel().getSelectedItem();
        if (selectedPhone == null) { showError("Please select a phone to update"); return; }

        List<String> errors = validatePhoneData();
        if (!errors.isEmpty()) { showErrors(errors); return; }

        updatePhoneFromFields(selectedPhone);
        clearFields();
        showSuccess("Phone updated successfully!");
    }

    @FXML
    void deletePhone(ActionEvent event) {
        Phone selectedPhone = phonesTable.getSelectionModel().getSelectedItem();
        if (selectedPhone == null) { showError("Please select a phone to delete"); return; }

        phoneStore.deletePhone(selectedPhone);
        clearFields();
        showSuccess("Phone deleted successfully!");
    }

    private List<String> validatePhoneData() {
        List<String> errors = new ArrayList<>();
        if (modelFld.getText().trim().isEmpty()) errors.add("Model is required");
        if (!isValidInteger(storageFld.getText())) errors.add("Invalid storage (GB)");
        if (!isValidDouble(priceFld.getText())) errors.add("Invalid price");
        return errors;
    }

    private boolean isValidInteger(String value) {
        try { Integer.parseInt(value.trim()); return true; } catch (Exception e) { return false; }
    }

    private boolean isValidDouble(String value) {
        try { Double.parseDouble(value.trim()); return true; } catch (Exception e) { return false; }
    }

    private Phone createPhoneFromFields() {
        return new Phone(
                modelFld.getText().trim(),
                Integer.parseInt(storageFld.getText().trim()),
                Double.parseDouble(priceFld.getText().trim())
        );
    }

    private void updatePhoneFromFields(Phone phone) {
        phoneStore.updatePhone(
                phone,
                modelFld.getText().trim(),
                Integer.parseInt(storageFld.getText().trim()),
                Double.parseDouble(priceFld.getText().trim())
        );
    }

    private void clearFields() {
        modelFld.clear();
        storageFld.clear();
        priceFld.clear();
        phonesTable.getSelectionModel().clearSelection();
    }

    private void showSuccess(String message) {
        errorMsg.setText(message);
        errorMsg.setStyle("-fx-fill: green;");
    }

    private void showError(String message) {
        errorMsg.setText(message);
        errorMsg.setStyle("-fx-fill: red;");
    }

    private void showErrors(List<String> errors) {
        errorMsg.setText(String.join("\n", errors));
        errorMsg.setStyle("-fx-fill: red;");
    }
}