package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myquran.entities.fragments.HistoriqueFragment;
import com.example.myquran.entities.fragments.PagesHistoriqueFragment;
import com.example.myquran.entities.fragments.PlusLusFragment;

public class StatsHisActivity extends AppCompatActivity {
    private Button histFragmentBtn;
    private Button statPageFragmentBtn;
    private Button statSurahFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_his);

        histFragmentBtn=findViewById(R.id.hist);
        statPageFragmentBtn=findViewById(R.id.statsPage);
        statSurahFragmentBtn=findViewById(R.id.statsSurah);



        replaceFragment(new HistoriqueFragment());


        histFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HistoriqueFragment());
            }
        });


        statSurahFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PlusLusFragment());

            }
        });


        statPageFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PagesHistoriqueFragment());

            }
        });

    }
    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram_layout,fragment);
        fragmentTransaction.commit();
    }
}