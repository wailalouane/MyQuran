package com.example.myquran.entities.model;

public class HistoriqueModel {
    private String surahName;
    private int surahPage;
    private int id;

    public HistoriqueModel(String surahName, int surahPage, int id) {
        this.surahName = surahName;
        this.surahPage = surahPage;
        this.id = id;
    }

    public HistoriqueModel(String surahName, int surahPage) {
        this.surahName = surahName;
        this.surahPage = surahPage;
    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }

    public int getSurahPage() {
        return surahPage;
    }

    public void setSurahPage(int surahPage) {
        this.surahPage = surahPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
