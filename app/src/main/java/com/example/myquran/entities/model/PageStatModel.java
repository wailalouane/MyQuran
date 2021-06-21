package com.example.myquran.entities.model;

public class PageStatModel {

    private int indexDeb;
    private int indexFin;
    private int pageNum;

    public PageStatModel(int indexDeb, int indexFin, int pageNum) {
        this.indexDeb = indexDeb;
        this.indexFin = indexFin;
        this.pageNum = pageNum;
    }

    public PageStatModel() {

    }

    public int getIndexDeb() {
        return indexDeb;
    }

    public void setIndexDeb(int indexDeb) {
        this.indexDeb = indexDeb;
    }

    public int getIndexFin() {
        return indexFin;
    }

    public void setIndexFin(int indexFin) {
        this.indexFin = indexFin;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
