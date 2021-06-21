package com.example.myquran.entities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myquran.Adapter.RecyclePageHistoAdapter;
import com.example.myquran.Adapter.RecyclePlusLusAdapter;
import com.example.myquran.R;
import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;


public class PagesHistoriqueFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_pages_historique,container,false);

        List<PageModel> pageModelList=new ArrayList<>();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(rootView.getContext());

        pageModelList=dataBaseHelper.getPagesStatCount();

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        recyclerView=rootView.findViewById(R.id.recycleHistoriquePages);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecyclePageHistoAdapter(pageModelList,rootView.getContext());

        recyclerView.setAdapter(adapter);

        return rootView;
    }
}