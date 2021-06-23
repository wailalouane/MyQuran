package com.example.myquran.entities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myquran.Adapter.RecycleHistoAdapter;
import com.example.myquran.Adapter.RecyclePageHistoAdapter;
import com.example.myquran.R;
import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.model.HistoriqueModel;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;


public class HistoriqueFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_historique,container,false);
        List<HistoriqueModel> historiqueModelsList=new ArrayList<>();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(rootView.getContext());
        historiqueModelsList=dataBaseHelper.getAllHistorique();

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        recyclerView=rootView.findViewById(R.id.recycleHistorique);

        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecycleHistoAdapter(historiqueModelsList,rootView.getContext());
        if(historiqueModelsList.isEmpty()){
            TextView nothing=rootView.findViewById(R.id.nothing_to_show);
            nothing.setText("l'historique est vide");
        }

        recyclerView.setAdapter(adapter);

        return rootView;
    }
}