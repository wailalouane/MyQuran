package com.example.myquran.entities.model;

public class SurahModel {
    private int idSurah;
    private String nameSurahArabe;
    private String nameSurah;
    private int startSurahPage;


    public SurahModel(int idSurah, String nameSurahArabe, String nameSurah, int startSurahPage) {
        this.nameSurahArabe=nameSurahArabe;
        this.idSurah = idSurah;
        this.nameSurah = nameSurah;
        this.startSurahPage = startSurahPage;
    }

    public String getNameSurahArabe() {
        return nameSurahArabe;
    }

    public void setNameSurahArabe(String nameSurahArabe) {
        this.nameSurahArabe = nameSurahArabe;
    }
    public int getIdSurah() {
        return idSurah;
    }

    public void setIdSurah(int idSurah) {
        this.idSurah = idSurah;
    }

    public String getNameSurah() {
        return nameSurah;
    }

    public void setNameSurah(String nameSurah) {
        this.nameSurah = nameSurah;
    }

    public int getStartSurahPage() {
        return startSurahPage;
    }

    public void setStartSurahPage(int startSurahPage) {
        this.startSurahPage = startSurahPage;
    }
}
