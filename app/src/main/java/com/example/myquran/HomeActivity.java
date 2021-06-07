package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myquran.entities.list.SurahList;
import com.example.myquran.entities.model.SurahModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements RecycleViewAdapter.OnSurahListener {


    //surah list
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //liste to show
    SurahList SurahList = new SurahList();
    ArrayList<SurahModel> surahList=new ArrayList<>();


    //search about surah
    SearchView searchView;

    //historique list
    private RecyclerView mRecyclerViewHis;
    ArrayList<SurahModel> surahListHis=new ArrayList<>();



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
    }

    public void historiqueList(){

        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));
        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));
        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 405));
        surahListHis.add(new SurahModel(7, "الأعراف", "Al-A'raf (The Heights)", 151));
        surahListHis.add(new SurahModel(30, "الروم", "Ar-Rum (The Romans)", 406));





        mRecyclerViewHis=(RecyclerView) findViewById(R.id.historique);
        mRecyclerViewHis.setHasFixedSize(true);
      //  mLayoutManager = new LinearLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
      //  mLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewHis.setLayoutManager(layoutManager);
        mAdapter =new RecycleViewHistAdapter(surahListHis,HomeActivity.this);
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

            Toast.makeText(HomeActivity.this,position,Toast.LENGTH_SHORT).show();
            /*Toast.makeText(HomeActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();*/
            startActivity(intent);

        }

    }
}
