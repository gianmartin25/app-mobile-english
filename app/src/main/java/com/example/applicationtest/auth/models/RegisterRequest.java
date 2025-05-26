package com.example.applicationtest.auth.models;


public class RegisterRequest {
    private String name;
    private String lastname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getLearningGoals() {
        return learningGoals;
    }

    public void setLearningGoals(String learningGoals) {
        this.learningGoals = learningGoals;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String englishLevel;
    private String learningGoals;
    private String password;

    public RegisterRequest(String name, String lastname, String email, String englishLevel, String learningGoals, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.englishLevel = englishLevel;
        this.learningGoals = learningGoals;
        this.password = password;
    }

    // Getters y setters (opcional)
}