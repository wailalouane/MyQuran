package com.example.myquran.entities.model;
import java.util.ArrayList;
import java.util.Map;

public class Functions {
    public  static  String ChangetoArabic(int number){
        return "﴿"+number+"﴾";
    }

    public  static ArrayList<Integer> GetPosAyah(String Ayahs){
        ArrayList arrayList= new ArrayList();
        char[] chars =Ayahs.toCharArray();
        for (int i =0;i<Ayahs.length();i++){
            if (Character.isDigit(Ayahs.charAt(i))){
                arrayList.add(i);
            }
        }
        return arrayList;

    }
}

