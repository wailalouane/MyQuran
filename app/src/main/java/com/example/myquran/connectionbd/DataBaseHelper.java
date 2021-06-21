package com.example.myquran.connectionbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myquran.entities.model.HistoriqueModel;
import com.example.myquran.entities.model.PageModel;
import com.example.myquran.entities.model.PageStatModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    //------------------ TABLE NAME-------------------//
    public static final String PAGES_STAT="PAGES_STAT";
    public static final String HISTORIQUE = "HISTORIQUE";
    public static final String MOSTREAD = "MOSTREAD";
    public static final String PAGES = "PAGES";
    public static final String SURAH_PAGE = "SURAH_PAGE";


    //------------------COLOMN------------------------//
    public static final String SURAH_NAME = "SURAH_NAME";
    public static final String ID = "ID";
    public static final String IDP = "IDp";
    public static final String SURAH_TEXT = "SURAH_TEXT";
    public static final String SURAH_PAGES = "SURAH_PAGES";
    public static final String CPT = "CPT";
    public static final String INDEX_DEB = "INDEX_DEB";
    public static final String INDEX_FIN = "INDEX_FIN";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "myquran.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatementPagesStat="CREATE TABLE " + PAGES_STAT + " (" + IDP + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INDEX_DEB + " INT," + INDEX_FIN + " INT, " + SURAH_PAGES + " INT)";
        String createTableStatementMostRead="CREATE TABLE " +MOSTREAD+ " (" + IDP + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SURAH_NAME + " TEXT, " + SURAH_PAGES + " INT, " + CPT + " INT)";
        String createTableStatementPAGES= "CREATE TABLE " + PAGES + " (" + IDP + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SURAH_TEXT + " TEXT, " + SURAH_PAGES + " INT)";
        String createTableStatement = "CREATE TABLE " + HISTORIQUE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURAH_NAME + " TEXT, " + SURAH_PAGE + " INT)";
        db.execSQL(createTableStatementMostRead);
        db.execSQL(createTableStatement);
        db.execSQL(createTableStatementPAGES);
        db.execSQL(createTableStatementPagesStat);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* if (newVersion > oldVersion) {
            String createTableStatementMostRead="CREATE TABLE " +MOSTREAD+ " (" + IDP + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SURAH_NAME + " TEXT, " + SURAH_PAGES + " INT, " + CPT + " INT)";
            String createTableStatement = "CREATE TABLE " + HISTORIQUE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURAH_NAME + " TEXT, " + SURAH_PAGE + " INT)";
            db.execSQL(createTableStatementMostRead);
            db.execSQL(createTableStatement);

        }*/
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

    public boolean addPagesText(PageModel pageModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(SURAH_PAGES,pageModel.getPageNum());
        cv.put(SURAH_TEXT,pageModel.getText());
        long insert = db.insert(PAGES, null, cv);
        if(insert==-1){return false;}
        else{
            return true;
        }
    }

    public boolean addPagesStatText(PageStatModel pageModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(INDEX_DEB,pageModel.getIndexDeb());
        cv.put(INDEX_FIN,pageModel.getIndexFin());
        cv.put(SURAH_PAGES,pageModel.getPageNum());
        long insert = db.insert(PAGES_STAT, null, cv);
        if(insert==-1){return false;}
        else{
            return true;
        }
    }
    public List<PageStatModel> getAllSubStringsPagesStat(int page){
        List<PageStatModel> returnList=new ArrayList<>();
        String sqlStatement="SELECT * FROM "+PAGES_STAT+" where "+SURAH_PAGES+" = "+page;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        if(cursor.moveToFirst()){
            do{
                int idPages=cursor.getInt(0);
                int indexDeb=cursor.getInt(1);
                int indexFin=cursor.getInt(2);
                int surahPage=cursor.getInt(3);
                PageStatModel pageModel=new PageStatModel(indexDeb,indexFin,surahPage);
                returnList.add(pageModel);
            }while(cursor.moveToNext());
        }else{
            //nothing
        }

        return returnList;
    }
    public boolean deleteSubStringsPagesStat(int page){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        String query="DELETE FROM "+PAGES_STAT+" WHERE "+SURAH_PAGES+" = "+page;
        Cursor cursor= sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

    public List<PageModel> getAllSubStringsPages(int page){
        List<PageModel> returnList=new ArrayList<>();
        String sqlStatement="SELECT * FROM "+PAGES+" where "+SURAH_PAGE+" = "+page;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        if(cursor.moveToFirst()){
            do{
                int idPages=cursor.getInt(0);
                String surahText=cursor.getString(1);
                int surahPage=cursor.getInt(2);
                PageModel pageModel=new PageModel(surahPage,surahText,idPages);
                returnList.add(pageModel);
            }while(cursor.moveToNext());
        }else{
            //nothing
        }

        return returnList;
    }
    public boolean deleteSubStringsPages(int page){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        String query="DELETE FROM "+PAGES+" WHERE "+SURAH_PAGE+" = "+page;
        Cursor cursor= sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

    public List<PageModel> getPagesStatCount(){
        List<PageModel> returnList=new ArrayList<>();
        String sqlStatement="select "+SURAH_PAGES+ ",count(SURAH_PAGES) as " + CPT + " from " +PAGES_STAT+" group by "+SURAH_PAGES+ " order by " + CPT + " DESC";



        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        if(cursor.moveToFirst()){
            do {
                int surahPages=cursor.getInt(0);
                int cptPage=cursor.getInt(1);
                PageModel pageModel=new PageModel(surahPages,cptPage);
                returnList.add(pageModel);
            }while(cursor.moveToNext());
        }else{
            //nothing
        }


        return returnList;
    }


    public List<PageModel> getPagesCount(){
        List<PageModel> returnList=new ArrayList<>();
        String sqlStatement="select "+SURAH_PAGES+ ",count(SURAH_PAGES) as " + CPT + " from " +PAGES+" group by "+SURAH_PAGES+ " order by " + CPT + " DESC";
        String sqlStatment2="select * from " +PAGES;


        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        if(cursor.moveToFirst()){
            do {
                int surahPages=cursor.getInt(0);
                int cptPage=cursor.getInt(1);
                PageModel pageModel=new PageModel(surahPages,cptPage);
                returnList.add(pageModel);
            }while(cursor.moveToNext());
        }else{
            //nothing
        }


        return returnList;
    }


    private boolean addToMostReadprv(PageModel pageModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(SURAH_NAME,pageModel.getPageTitle());
        cv.put(SURAH_PAGES,pageModel.getPageNum());
        cv.put(CPT,1);
        long insert = db.insert(MOSTREAD, null, cv);
        if(insert==-1){return false;}
        else{
            return true;
        }
    }

    public void addToMostRead(PageModel pageModel){
        String sqlStatement="SELECT * FROM "+MOSTREAD;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        Boolean trv=false;
        if(cursor.moveToFirst()){
            do {
                int surahPages=cursor.getInt(0);
                String surahName=cursor.getString(1);
                int surahPage=cursor.getInt(2);
                if(surahName.equals(pageModel.getPageTitle())){
                    db.close();
                    db=this.getWritableDatabase();
                    sqlStatement="UPDATE "+MOSTREAD+" SET "+CPT+"="+CPT+"+1 WHERE "+SURAH_PAGES+"="+pageModel.getPageNum()+" ";
                    db.execSQL(sqlStatement);
                    db.close();
                    trv=true;
                    break;
                }

            }while(cursor.moveToNext());
        }
            if(trv==false){
                db.close();
                addToMostReadprv(pageModel);
            }





    }

    public List<PageModel> getAllMostReadPage(){
        List<PageModel> returnList=new ArrayList<>();
        String sqlStatement="SELECT * FROM "+MOSTREAD;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStatement,null);
        if(cursor.moveToFirst()){
            do{
                int idPages=cursor.getInt(0);
                String surahName=cursor.getString(1);
                int surahPage=cursor.getInt(2);
                int cpt = cursor.getInt(3);
                PageModel pageModel=new PageModel(surahName,surahPage,cpt);
                returnList.add(pageModel);
            }while(cursor.moveToNext());
        }else{
            //nothing
        }

        return returnList;
    }

}
