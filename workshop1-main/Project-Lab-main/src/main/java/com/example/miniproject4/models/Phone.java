package com.example.miniproject4.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Phone {
    private final StringProperty model;
    private final IntegerProperty storage; // in GB
    private final DoubleProperty price; // in USD

    public Phone(String model, Integer storage, Double price) {
        this.model = new SimpleStringProperty(model);
        this.storage = new SimpleIntegerProperty(storage);
        this.price = new SimpleDoubleProperty(price);
    }

    // Model
    public String getModel() { return model.get(); }
    public void setModel(String value) { model.set(value); }
    public StringProperty modelProperty() { return model; }

    // Storage
    public int getStorage() { return storage.get(); }
    public void setStorage(int value) { storage.set(value); }
    public IntegerProperty storageProperty() { return storage; }

    // Price
    public double getPrice() { return price.get(); }
    public void setPrice(double value) { price.set(value); }
    public DoubleProperty priceProperty() { return price; }

    // Copy constructor
    public Phone(Phone other) {
        this(other.getModel(), other.getStorage(), other.getPrice());
    }

    // Optional builder
    public static class Builder {
        private String model;
        private Integer storage = 64;
        private Double price = 0.0;

        public Builder model(String model) { this.model = model; return this; }
        public Builder storage(Integer storage) { this.storage = storage; return this; }
        public Builder price(Double price) { this.price = price; return this; }
        public Phone build() { return new Phone(model, storage, price); }
    }
}