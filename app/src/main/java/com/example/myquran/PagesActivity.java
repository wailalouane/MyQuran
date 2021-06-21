package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.fragments.PageFragment1;
import com.example.myquran.entities.fragments.PageFragment10;
import com.example.myquran.entities.fragments.PageFragment2;
import com.example.myquran.entities.fragments.PageFragment3;
import com.example.myquran.entities.fragments.PageFragment4;
import com.example.myquran.entities.fragments.PageFragment5;
import com.example.myquran.entities.fragments.PageFragment6;
import com.example.myquran.entities.fragments.PageFragment7;
import com.example.myquran.entities.fragments.PageFragment8;
import com.example.myquran.entities.fragments.PageFragment9;
import com.example.myquran.entities.model.HistoriqueModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class PagesActivity extends AppCompatActivity {


 private ViewPager mPager;
  private PagerAdapter mPagerAdapter;

    List<Fragment> list =new ArrayList<>();
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        Intent intent =getIntent();


        // ici passage d'argument par l'activity HOME
        int pos=intent.getIntExtra("position",0);

        lesFragment(pos);//ouvrir le fragment qu'il faut
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }


    @Override
    public void onBackPressed(){
        int x=mPager.getCurrentItem();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(PagesActivity.this);

        String surahName="";
         switch (x){
             case 9: case 8:
                 surahName="سورة الملك";
                 break;
             case 7: case 6:
                 surahName= "سورة القلم";
                 break;
             case 5: case 4:
                 surahName="سورة الحاقة";
                 break;
             case 3 : case 2:
                 surahName="سورة المعارج";
                 break;
             case 1: case 0:
                 surahName="سورة نوح";
                 break;

         }
        x=571-x;//x now is the current page when we press back button
        HistoriqueModel historiqueModel=new HistoriqueModel(surahName,x);
        boolean added = dataBaseHelper.addHistorique(historiqueModel);
        Toast.makeText(PagesActivity.this,"added item"+added,Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this,surahName+x,Toast.LENGTH_SHORT).show();

        Intent intent =new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();


        /*super.onBackPressed();*/
    }


    public void lesFragment(int pos){


        // la liste des fragment

        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());
        list.add(new PageFragment4());
        list.add(new PageFragment5());
        list.add(new PageFragment6());
        list.add(new PageFragment7());
        list.add(new PageFragment8());
        list.add(new PageFragment9());
        list.add(new PageFragment10());


        Collections.reverse(list);//reverse pour lire de droite a gauche (en arabe)


        mPager=findViewById(R.id.pager);
        String json = loadJSONFromAsset();//pour passer le fichier jon en parametre

        mPagerAdapter=new SlidePageAdapter(getSupportFragmentManager(),list,json);//appeler le constructure de slide page(fragment)
        mPager.setAdapter(mPagerAdapter);
        if(pos<572&&pos>561){
            mPager.setCurrentItem(571-pos);

        }else{
            mPager.setCurrentItem(9);//ici ouvrir la page qu'il faut on va utilisé le variable pos (571-pos)
        }


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


