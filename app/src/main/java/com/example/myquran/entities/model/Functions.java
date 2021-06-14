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
    public static int GetIndexOfFirstWord(String s,int PositionOfAyahSymbol){
        char[] arrayList = s.toCharArray();
        for (int i =PositionOfAyahSymbol;i<s.length();i++){



                if (arrayList[i] ==' ')
                    return i;
            }



        return -1;
    }
    public  static int GetIndexOfSecondWord(String s,int PositionOfAyahSymbol){


        return GetIndexOfFirstWord(s,GetIndexOfFirstWord(s,PositionOfAyahSymbol)+1);
    }
    public static int GetIndexOfLastWord(String s ,int PositionOfAyahSymbol){
        char[] arrayList = s.toCharArray();
        int  pos = 0;
        for (int i =PositionOfAyahSymbol;i>0;i--){
            if (arrayList[i]==' ')
                return i;
        }
                return -1;
    }
}

