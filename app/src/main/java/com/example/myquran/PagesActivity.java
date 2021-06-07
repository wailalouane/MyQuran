package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.myquran.entities.fragments.PageFragment1;
import com.example.myquran.entities.fragments.PageFragment2;
import com.example.myquran.entities.fragments.PageFragment3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class PagesActivity extends AppCompatActivity {


 private ViewPager mPager;
  private PagerAdapter mPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        Intent intent =getIntent();


        // ici passage d'argument par l'activity HOME
        int pos=intent.getIntExtra("position",0);

        lesFragment(pos);//ouvrir le fragment qu'il faut


    }



    public void lesFragment(int pos){


        // la liste des fragment
        List<Fragment> list =new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());


        Collections.reverse(list);//reverse pour lire de droite a gauche (en arabe)


        mPager=findViewById(R.id.pager);
        String json = loadJSONFromAsset();//pour passer le fichier jon en parametre

        mPagerAdapter=new SlidePageAdapter(getSupportFragmentManager(),list,json);//appeler le constructure de slide page(fragment)
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(2);//ici ouvrir la page qu'il faut on va utilisé le variable pos

    }

    public String loadJSONFromAsset() {  //methode khchina hadi matksserch rassek m3aha
        String json = null;
        try {
            InputStream is = getAssets().open("quran.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



}


