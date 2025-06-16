package com.example.applicationtest.models;

import java.util.List;

public class Verb {
    private int id;
    private String wordName;
    private String typeName;
    private List<String> images;
    private List<Pronunciation> pronunciations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public static class Pronunciation {
        private String locale;
        private String phonetic;
        private String audioUrl;
        private List<Phoneme> phonemes;

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public List<Phoneme> getPhonemes() {
            return phonemes;
        }

        public void setPhonemes(List<Phoneme> phonemes) {
            this.phonemes = phonemes;
        }
    }

    public static class Phoneme {
        private String symbol;
        private String audioUrl;
        private String example;
        private String exampleAudioUrl;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public String getExample() {
            return example;
        }

        public void setExample(String example) {
            this.example = example;
        }

        public String getExampleAudioUrl() {
            return exampleAudioUrl;
        }

        public void setExampleAudioUrl(String exampleAudioUrl) {
            this.exampleAudioUrl = exampleAudioUrl;
        }
    }
}