package com.example.myquran.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlideHelpAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentlist;

    public SlideHelpAdapter(FragmentManager fragmentManager, List<Fragment> fragmentlist) {
        super(fragmentManager);
        this.mFragmentlist= fragmentlist;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentlist.size();
    }
}
