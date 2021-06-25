package com.example.myquran.entities.model;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.myquran.connectionbd.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;
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
    public static SpannableString loadPages(TextView textView,int page){
        SpannableString returtext= (SpannableString) textView.getText();
        List<PageStatModel> pageStatModelList=new ArrayList<>();
        ForegroundColorSpan fcswhite = new ForegroundColorSpan(Color.WHITE);
        returtext.setSpan(fcswhite,0,returtext.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);// la page blach

        DataBaseHelper dataBaseHelper=new DataBaseHelper(textView.getContext());
       pageStatModelList=dataBaseHelper.getAllSubStringsPagesStat(page);
        for (int i = 0; i < pageStatModelList.size(); i++) {
            ForegroundColorSpan fcsred=new ForegroundColorSpan(Color.RED);
            int start=pageStatModelList.get(i).getIndexDeb();
            int end=pageStatModelList.get(i).getIndexFin();
            returtext.setSpan(fcsred,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return returtext;
    }


    public static boolean thereIsStat(int page,TextView textView){
        return new DataBaseHelper(textView.getContext()).getAllSubStringsPagesStat(page).isEmpty();
    }
}

