package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myquran.Adapter.SlideHelpAdapter;
import com.example.myquran.entities.fragments.HelpFragment1;
import com.example.myquran.entities.fragments.HelpFragment2;
import com.example.myquran.entities.fragments.HelpFragment3;
import com.example.myquran.entities.fragments.HelpFragment4;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        List<Fragment> list=new ArrayList<>();
        list.add(new HelpFragment1());
        list.add(new HelpFragment2());
        list.add(new HelpFragment3());
        list.add(new HelpFragment4());


        pager=findViewById(R.id.helpPager);
        mPagerAdapter=new SlideHelpAdapter(getSupportFragmentManager(),list);

        pager.setAdapter(mPagerAdapter);

    }
}