package com.example.miniproject4.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PhonesStore {
    private final ObservableList<Phone> phones = FXCollections.observableArrayList();

    public PhonesStore() {
        // Start with an empty list; user will add phones manually.
    }

    public ObservableList<Phone> getPhonesList() {
        return phones;
    }

    public boolean addPhone(Phone phone) {
        if (phone == null) return false;
        return phones.add(phone);
    }

    public boolean deletePhone(Phone phone) {
        if (phone == null) return false;
        return phones.remove(phone);
    }

    public boolean updatePhone(Phone phone, String model, Integer storage, Double price) {
        if (phone == null || !phones.contains(phone)) return false;
        phone.setModel(model);
        phone.setStorage(storage);
        phone.setPrice(price);
        return true;
    }

    public Optional<Phone> findPhone(String model, Integer storage) {
        return phones.stream()
                .filter(p -> p.getModel().equalsIgnoreCase(model) && p.getStorage() == storage)
                .findFirst();
    }

    public List<Phone> filterByModel(String model) {
        return phones.stream()
                .filter(p -> p.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Phone> filterByPriceRange(double minPrice, double maxPrice) {
        return phones.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Integer> getAllStorages() {
        return phones.stream()
                .map(Phone::getStorage)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public int getPhoneCount() { return phones.size(); }
    public void clearAllPhones() { phones.clear(); }
    public boolean contains(Phone phone) { return phone != null && phones.contains(phone); }
}