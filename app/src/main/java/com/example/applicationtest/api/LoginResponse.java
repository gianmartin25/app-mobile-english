package com.example.applicationtest.api;

import java.io.Serializable;

public class LoginResponse {
    private String access_token;
    private User user;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class User implements Serializable {
        public User(String name, String lastname, String email, String englishLevel, String learningGoals, String profileImage) {
            this.name = name;
            this.lastname = lastname;
            this.email = email;
            this.englishLevel = englishLevel;
            this.learningGoals = learningGoals;
            this.profileImage = profileImage;
        }

        private int id;
        private String name;
        private String lastname;
        private String email;
        private String englishLevel;
        private String learningGoals;
        private String createdAt;
        private String updatedAt;
        private String  profileImage;

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

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }
    }
}