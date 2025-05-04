package com.example.applicationtest.models;

import java.util.List;

public class Verb {
    private List<CountryVerb> countriesVerb;
    private List<String> images;

    public List<CountryVerb> getCountriesVerb() {
        return countriesVerb;
    }

    public void setCountriesVerb(List<CountryVerb> countriesVerb) {
        this.countriesVerb = countriesVerb;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class CountryVerb {
        private String country;
        private String verbName;
        private String audioUrl;
        private String phonetic;
        private List<Character> characters;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getVerbName() {
            return verbName;
        }

        public void setVerbName(String verbName) {
            this.verbName = verbName;
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

        public List<Character> getCharacters() {
            return characters;
        }

        public void setCharacters(List<Character> characters) {
            this.characters = characters;
        }
    }

    public static class Character {
        private String phoneticCharacter;
        private String wordExample;
        private String audioCharacterUrl;

        public String getPhoneticCharacter() {
            return phoneticCharacter;
        }

        public void setPhoneticCharacter(String phoneticCharacter) {
            this.phoneticCharacter = phoneticCharacter;
        }

        public String getWordExample() {
            return wordExample;
        }

        public void setWordExample(String wordExample) {
            this.wordExample = wordExample;
        }

        public String getAudioCharacterUrl() {
            return audioCharacterUrl;
        }

        public void setAudioCharacterUrl(String audioCharacterUrl) {
            this.audioCharacterUrl = audioCharacterUrl;
        }
    }
}