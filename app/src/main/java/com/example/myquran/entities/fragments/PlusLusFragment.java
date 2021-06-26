package com.example.myquran.entities.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myquran.Adapter.RecyclePlusLusAdapter;
import com.example.myquran.HomeActivity;
import com.example.myquran.PagesActivity;
import com.example.myquran.R;
import com.example.myquran.connectionbd.DataBaseHelper;
import com.example.myquran.entities.model.Functions;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;


public class PlusLusFragment extends Fragment implements RecyclePlusLusAdapter.OnMostReadListener {
    View viewIN;
    int page;
    List<PageModel> pageModelLists=new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_plus_lus,container,false);
        viewIN=rootView;
        List<PageModel> pageModelList=new ArrayList<>();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(rootView.getContext());
        pageModelList=dataBaseHelper.getAllMostReadPage();

        pageModelList= Functions.SortPageModelArray(pageModelList);
        pageModelLists=pageModelList;
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        recyclerView=rootView.findViewById(R.id.plusLusHistorique);

        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecyclePlusLusAdapter(pageModelList,rootView.getContext(),this);
        if(pageModelList.isEmpty()){
            TextView nothing=rootView.findViewById(R.id.nothing_to_show);
            nothing.setText("historiue est vide ");
        }
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onMostReadClick(int position) {
        page=pageModelLists.get(position).getPageNum();
        Intent intent =new Intent(viewIN.getContext(), PagesActivity.class);
        intent.putExtra("position",page);
        startActivity(intent);
    }
}