package com.example.myquran.entities.fragments;

import android.content.Intent;
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
import com.example.myquran.PagesActivity;
import com.example.myquran.R;
import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.model.HistoriqueModel;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;


public class HistoriqueFragment extends Fragment implements RecycleHistoAdapter.OnHistListener{
    int page;
    View viewIN;

    List<HistoriqueModel> historiqueModelsLists=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_historique,container,false);
        List<HistoriqueModel> historiqueModelsList=new ArrayList<>();
        viewIN=rootView;
        DataBaseHelper dataBaseHelper=new DataBaseHelper(rootView.getContext());
        historiqueModelsList=dataBaseHelper.getAllHistorique();
        historiqueModelsLists=historiqueModelsList;
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        recyclerView=rootView.findViewById(R.id.recycleHistorique);

        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecycleHistoAdapter(historiqueModelsList,rootView.getContext(),this);
        if(historiqueModelsList.isEmpty()){
            TextView nothing=rootView.findViewById(R.id.nothing_to_show);
            nothing.setText("l'historique est vide");
        }

        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onHistClick(int position) {
        page=historiqueModelsLists.get(position).getSurahPage();
        Intent intent =new Intent(viewIN.getContext(), PagesActivity.class);
        intent.putExtra("position",page);
        startActivity(intent);
    }
}