package com.example.applicationtest.auth.models;


public class RegisterResponse {
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String access_token;

    public static class User {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        private int id;
        private String name;
        private String lastname;
        private String email;
        private String password;
        private String englishLevel;
        private String learningGoals;
        private String createdAt;
        private String updatedAt;

        // Getters y setters (opcional)
    }

    // Getters y setters (opcional)
}