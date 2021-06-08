package com.example.myquran;
import android.app.Application;
import android.widget.TextView;

public class MyQuranApp extends Application {

    private TextView surahText;

    public TextView getSurahText() {
        return surahText;
    }

    public void setSurahText(TextView value) {
        this.surahText = value;
    }
}