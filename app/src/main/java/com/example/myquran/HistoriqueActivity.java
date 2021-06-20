package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myquran.Adapter.HistoriqueVpAdapter;
import com.example.myquran.entities.fragments.HistoriqueFragment;
import com.example.myquran.entities.fragments.PagesHistoriqueFragment;
import com.example.myquran.entities.fragments.PlusLusFragment;
import com.google.android.material.tabs.TabLayout;

public class HistoriqueActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);


        mTabLayout=findViewById(R.id.tablayout);
        mViewPager=findViewById(R.id.viewPager);

        mTabLayout.setupWithViewPager(mViewPager);

        HistoriqueVpAdapter historiqueVpAdapter =new HistoriqueVpAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        historiqueVpAdapter.addFragment(new PagesHistoriqueFragment(),"PAGES");
        historiqueVpAdapter.addFragment(new HistoriqueFragment(),"HISTORIQUE");
        historiqueVpAdapter.addFragment(new PlusLusFragment(),"PLUS LUS");
        // here add adapter


        mViewPager.setAdapter(historiqueVpAdapter);

    }


}