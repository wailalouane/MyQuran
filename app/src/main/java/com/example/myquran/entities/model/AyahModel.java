package com.example.myquran.entities.model;

public class AyahModel {
    private int number;
    private String text;
    private int numberInSurah;
    private int juz;
    private int page;
    boolean sajda;
    private String surahName;

    public AyahModel(int number, String text, int numberInSurah, int juz, int page, boolean sajda,String surahName) {
        this.number = number;
        this.text = text;
        this.numberInSurah = numberInSurah;
        this.juz = juz;
        this.page = page;
        this.sajda = sajda;
        this.surahName=surahName;
    }

    public AyahModel() {

    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumberInSurah() {
        return numberInSurah;
    }

    public void setNumberInSurah(int numberInSurah) {
        this.numberInSurah = numberInSurah;
    }

    public int getJuz() {
        return juz;
    }

    public void setJuz(int juz) {
        this.juz = juz;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isSajda() {
        return sajda;
    }

    public void setSajda(boolean sajda) {
        this.sajda = sajda;
    }


    public String toString(){
        if(this.numberInSurah==0){
            return this.text;
        }else{
            String returned =this.text+" ("+this.numberInSurah+")";
            return returned;
        }

    }
}
