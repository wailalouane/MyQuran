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

public class HisStatActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_stat);
        mTabLayout=findViewById(R.id.tabLayout);
        mViewPager=findViewById(R.id.viewPager);
        mTabLayout.setupWithViewPager(mViewPager);

        HistoriqueVpAdapter historiqueVpAdapter =new HistoriqueVpAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        historiqueVpAdapter.addFragment(new PagesHistoriqueFragment(),"احصائيات الصفحات");
        historiqueVpAdapter.addFragment(new HistoriqueFragment(),"السور التي تم\n زيارتها مؤخرا");
        historiqueVpAdapter.addFragment(new PlusLusFragment(),"السور الأكثر زيارة");
        mViewPager.setAdapter(historiqueVpAdapter);
    }
}