package com.example.myquran.entities.model;

import java.io.InputStream;
import java.util.ArrayList;

public class PageModel {
    private int pageNum;
    private String pageTitle;

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

    public void setOnePageList(ArrayList<AyahModel> onePageList) {
        this.onePageList = onePageList;
    }

    ArrayList<AyahModel> onePageList=new ArrayList<>();

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

    public PageModel(int pageNum, String pageTitle, ArrayList<AyahModel> onePageList) {
        this.pageNum = pageNum;
        this.pageTitle = pageTitle;
        this.onePageList = onePageList;
    }
    public PageModel() {

    }
}
