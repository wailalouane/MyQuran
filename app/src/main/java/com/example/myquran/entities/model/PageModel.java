package com.example.myquran.entities.model;

import java.io.InputStream;
import java.util.ArrayList;

public class PageModel {
    private int pageNum;
    private String pageTitle;
    private String text;
    private int id;
    private int cpt;
    ArrayList<AyahModel> onePageList=new ArrayList<>();

    //CONSTROCTORS
    public PageModel(int pageNum, String text, int id) {
        this.pageNum = pageNum;
        this.text = text;
        this.id = id;
    }
    public PageModel(int pageNum, String pageTitle, ArrayList<AyahModel> onePageList) {
        this.pageNum = pageNum;
        this.pageTitle = pageTitle;
        this.onePageList = onePageList;
    }
    public PageModel() {

    }
    public PageModel(int pageNum, int cpt) {
        this.pageNum = pageNum;
        this.cpt = cpt;
    }

    public PageModel(String surahName,int pageNum,int cpt){
        this.pageTitle=surahName;
        this.pageNum=pageNum;
        this.cpt=cpt;
    }


    // GETTERS AND SETTERS
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public ArrayList<AyahModel> getOnePageList() {
        return onePageList;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public void setOnePageList(ArrayList<AyahModel> onePageList) {
        this.onePageList = onePageList;
    }






    //METHODES
    public void loadOnePage(int pageNum){
        String json;

    }
    public void addAyah(AyahModel ayahModel){
        onePageList.add(ayahModel);

    }

    public String showPage(){

        String text="";
        StringBuffer textt=new StringBuffer();
        for (int i = 0; i <this.onePageList.size() ; i++) {
         //   text=text+this.onePageList.get(i).toString()+" ";
            textt.append(this.onePageList.get(i).getText());
        }

        return textt.toString();
    }




}
