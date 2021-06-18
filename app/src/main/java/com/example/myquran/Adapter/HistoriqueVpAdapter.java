package com.example.myquran.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class HistoriqueVpAdapter extends FragmentPagerAdapter {

    private  final ArrayList<Fragment> mFragmentArrayList=new ArrayList<>();
    private final ArrayList<String> fragmentTitle=new ArrayList<>();

    public HistoriqueVpAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull

    @Override
    public Fragment getItem(int position) {
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }

    public void addFragment(Fragment fragment,String title){
        mFragmentArrayList.add(fragment);
        fragmentTitle.add(title);

    }


    @Nullable

    @Override
    public CharSequence getPageTitle(int position) {


        return fragmentTitle.get(position);
    }
}
