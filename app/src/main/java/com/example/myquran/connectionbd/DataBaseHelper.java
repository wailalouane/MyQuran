package com.example.myquran.connectionbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myquran.entities.model.HistoriqueModel;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String HISTORIQUE = "HISTORIQUE";
    public static final String SURAH_NAME = "SURAH_NAME";
    public static final String SURAH_PAGE = "SURAH_PAGE";
    public static final String ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "myquran.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + HISTORIQUE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURAH_NAME + " TEXT, " + SURAH_PAGE + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addHistorique(HistoriqueModel historiqueModel){
         SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(SURAH_NAME,historiqueModel.getSurahName());
        cv.put(SURAH_PAGE,historiqueModel.getSurahPage());
        long insert = db.insert(HISTORIQUE, null, cv);
        if(insert==-1){return false;}
        else{
            return true;
        }

    }
}
