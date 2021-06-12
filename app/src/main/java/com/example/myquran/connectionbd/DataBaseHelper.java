package com.example.myquran.connectionbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myquran.entities.model.HistoriqueModel;

import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteHist(HistoriqueModel historiqueModel){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        String query="DELETE FROM "+HISTORIQUE+" WHERE "+SURAH_PAGE+" = "+historiqueModel.getSurahPage();
        Cursor cursor= sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
        return true;
            } else {
        return false;
}
    }


    public List<HistoriqueModel> getAllHistorique(){
        List<HistoriqueModel> returnList=new ArrayList<>();
        String sqlStatement="SELECT * FROM "+HISTORIQUE;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        if(cursor.moveToFirst()){
            do{
                int idHistorique=cursor.getInt(0);
                String surahName=cursor.getString(1);
                int surahPage=cursor.getInt(2);
                HistoriqueModel historiqueModel=new HistoriqueModel(surahName,surahPage,idHistorique);
                returnList.add(historiqueModel);
            }while(cursor.moveToNext());
        }else{
            //nothing
        }

        return returnList;
    }
}
