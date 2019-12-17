package com.platzi.functional._10_chaining.data;

public class Account {
    private String id;

    private String firstName;
    private String lastName;

    private double currentBalance;

    private Phone phone;

    public Account(String id, String firstName, String lastName, double currentBalance, Phone phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentBalance = currentBalance;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Phone getPhone() {
        return phone;
    }
}
