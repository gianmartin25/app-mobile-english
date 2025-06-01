package com.example.applicationtest.models;

import java.util.List;

public class Verb {
    private List<CountryWord> countriesWord;
    private List<String> images;

    public List<CountryWord> getCountriesWord() {
        return countriesWord;
    }

    public void setCountriesWord(List<CountryWord> countriesWord) {
        this.countriesWord = countriesWord;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class CountryWord {
        private String country;
        private String wordName;
        private String audioUrl;
        private String phonetic;
        private List<Phoeneme> characters;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getWordName() {
            return wordName;
        }

        public void setWordName(String wordName) {
            this.wordName = wordName;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public List<Phoeneme> getCharacters() {
            return characters;
        }

        public void setCharacters(List<Phoeneme> characters) {
            this.characters = characters;
        }
    }

    public static class Phoeneme {
        private String phoenemeName;
        private String wordExample;
        private String audioPhoenemeUrl;

        public String getPhoenemeName() {
            return phoenemeName;
        }

        public void setPhoenemeName(String phoenemeName) {
            this.phoenemeName = phoenemeName;
        }

        public String getWordExample() {
            return wordExample;
        }

        public void setWordExample(String wordExample) {
            this.wordExample = wordExample;
        }

        public String getAudioPhoenemeUrl() {
            return audioPhoenemeUrl;
        }

        public void setAudioPhoenemeUrl(String audioPhoenemeUrl) {
            this.audioPhoenemeUrl = audioPhoenemeUrl;
        }
    }
}