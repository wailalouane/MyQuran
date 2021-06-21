package com.example.myquran;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.list.SurahList;
import com.example.myquran.entities.model.HistoriqueModel;
import com.example.myquran.entities.model.PageModel;
import com.example.myquran.entities.model.SurahModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.example.myquran.MainActivity.setWindowFlag;

public class HomeActivity extends AppCompatActivity implements RecycleViewAdapter.OnSurahListener, RecycleViewHistAdapter.OnHistListener {


    //surah list
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView back_image;

    //liste to show
    SurahList SurahList = new SurahList();
    ArrayList<SurahModel> surahList=new ArrayList<>();


    //search about surah
    SearchView searchView;

    //historique list
    private RecyclerView mRecyclerViewHis;
    ArrayList<HistoriqueModel> surahListHis=new ArrayList<>();

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
        setContentView(R.layout.activity_home);





        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        historiqueList();
        surahList=SurahList.getSurahList();

        searchView=findViewById(R.id.search_bar);
        mRecyclerView=(RecyclerView) findViewById(R.id.listSurah);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        final RecycleViewAdapter recycleViewAdapter=new RecycleViewAdapter(surahList,HomeActivity.this,this);
        mAdapter = new RecycleViewAdapter(surahList,HomeActivity.this,this);
        mRecyclerView.setAdapter(recycleViewAdapter);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recycleViewAdapter.getFilter().filter(newText); return false;
            }
        });

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
    public void onBackPressed() {
        startActivity(new Intent(HomeActivity.this,Menu_activity.class));
        finish();
    }

    public void historiqueList(){

        /*surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));
        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));
        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));
        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));*/

        DataBaseHelper dataBaseHelper=new DataBaseHelper(HomeActivity.this);

        surahListHis= (ArrayList<HistoriqueModel>) dataBaseHelper.getAllHistorique();

        Collections.reverse(surahListHis);


        mRecyclerViewHis=(RecyclerView) findViewById(R.id.historique);
        mRecyclerViewHis.setHasFixedSize(true);
      //  mLayoutManager = new LinearLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
      //  mLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewHis.setLayoutManager(layoutManager);
        mAdapter =new RecycleViewHistAdapter(surahListHis,HomeActivity.this,this);
        mRecyclerViewHis.setAdapter(mAdapter);
    }


    @Override
    public void onSurahClick(int position) {


        if(position<66){
            Toast.makeText(HomeActivity.this,surahList.get(position).getNameSurahArabe()+"\n cette page n'est pa encore prête !",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent =new Intent(this,PagesActivity.class);
            // intent.putExtra()
            intent.putExtra("position",surahList.get(position).getStartSurahPage());


           /* intent.putExtra("la valeur","jooowez");*/

            Toast.makeText(HomeActivity.this,surahList.get(position).getNameSurahArabe(),Toast.LENGTH_SHORT).show();

            PageModel pageModel=new PageModel();
            pageModel.setPageTitle(surahList.get(position).getNameSurahArabe());
            pageModel.setPageNum(surahList.get(position).getStartSurahPage());
            DataBaseHelper dataBaseHelper=new DataBaseHelper(HomeActivity.this);
            dataBaseHelper.addToMostRead(pageModel);

            /*Toast.makeText(HomeActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();*/
            startActivity(intent);

        }

    }

    @Override
    public void onHistClick(int position) {
        HistoriqueModel historiqueModel =surahListHis.get(position);
        Intent intent =new Intent(this,PagesActivity.class);
        intent.putExtra("position",historiqueModel.getSurahPage());
        DataBaseHelper dataBaseHelper=new DataBaseHelper(HomeActivity.this);
        dataBaseHelper.deleteHist(historiqueModel);
        startActivity(intent);
    }
}
