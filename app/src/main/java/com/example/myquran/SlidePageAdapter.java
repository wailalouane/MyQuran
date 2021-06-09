package com.example.myquran;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myquran.entities.model.AyahModel;
import com.example.myquran.entities.model.Functions;
import com.example.myquran.entities.model.PageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class SlidePageAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragmentList;
    String json;
    String text;
    String titrePage;
    List<AyahModel> ayaList =new ArrayList<>();
    PageModel mPageModel;
    ArrayList<String> ayahText=new ArrayList<>();


    public SlidePageAdapter(FragmentManager fm,List<Fragment> fragmentList,String json){
        super(fm);
        this.mFragmentList=fragmentList;
        this.json=json;// the one of the JSon file
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle =new Bundle();
        text=new String();
      //  getSurahText(position);
        getPage(position);
        bundle.putString("json",text);
        bundle.putString("titre",titrePage);

        mFragmentList.get(position).setArguments(bundle);
        return mFragmentList.get(position);

    }



    public void getPage(int pos){
        AyahModel ayahModel = new AyahModel();
        try{
            JSONObject obj = new JSONObject(json);
            text="";
            String surahName = null;
            JSONObject data = obj.getJSONObject("data");
            JSONArray surahArray=data.getJSONArray("surahs");
            int surahindx=66;
            boolean finPage=false;
            while(!finPage){
                JSONObject surahobj=surahArray.getJSONObject(surahindx);
                surahName=surahobj.getString("name");
                JSONArray ayaArray = surahobj.getJSONArray("ayahs");
                int index=0;
                int page =571-pos;
                /*int page = 564-pos;*/
                while (!finPage&&index<ayaArray.length()){
                    if(ayaArray.getJSONObject(index).getInt("page")>page){
                        finPage=true;
                        break;
                    }

                    JSONObject ayaObj=ayaArray.getJSONObject(index);

                    if(page==ayaArray.getJSONObject(index).getInt("page")){
                        if(ayaObj.getInt("numberInSurah")==1){
                            ayaList.add(new AyahModel(0,surahName+"\n",0,ayaObj.getInt("juz"),ayaObj.getInt("page"),false,surahName));
                           // ayahText.add("R"+"\n");
                             ayahText.add(surahName+"\n");
                        }
                        text=text+ayaArray.getJSONObject(index).getString("text")+"("+ayaArray.getJSONObject(index).getInt("numberInSurah")+") ";

                        ayahText.add(ayaObj.getString("text")+ Functions.ChangetoArabic(ayaObj.getInt("numberInSurah")));

                    }
                    index++;
                   // ayaList.add(ayahModel);
                    if(ayaArray.length()==index){
                        surahindx++;

                    }
                }
            }
            StringBuffer texttt=new StringBuffer();
            for (int i = 0; i < ayahText.size(); i++) {

                texttt.append(ayahText.get(i)+" ");
            }
            ayahText.clear();
            text=texttt.toString();
            ayaList.clear();
            titrePage=surahName;


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


/*
    public void getSurahText(int pos){
         text ="textt";

        try {

            JSONObject obj =new JSONObject(json);
            text="";
            JSONObject data =obj.getJSONObject("data");
            JSONArray surahArray=data.getJSONArray("surahs");
            boolean finFragment=false;
            int surahindx=66;
            boolean finPage=false;
            while(!finPage){
                JSONObject surahobj=surahArray.getJSONObject(surahindx);
                JSONArray ayaArray = surahobj.getJSONArray("ayahs");

                int index=0;
                int page =564-pos;

                while (!finPage&&index<ayaArray.length()){
                    if(ayaArray.getJSONObject(index).getInt("page")>page){
                        finPage=true;
                        break;
                    }
                    if(page==ayaArray.getJSONObject(index).getInt("page")){
                        text=text+ayaArray.getJSONObject(index).getString("text")+"("+ayaArray.getJSONObject(index).getInt("numberInSurah")+") ";

                    }
                    index++;

                    if(ayaArray.length()==index){
                        surahindx++;

                    }

                }
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/

    @Override
    public int getCount() {
        return mFragmentList.size();
    }





}
