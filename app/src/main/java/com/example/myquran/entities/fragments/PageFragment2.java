package com.example.myquran.entities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myquran.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class PageFragment2 extends Fragment {

    String json;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle =getArguments();
        json = bundle.getString("json");
        String pagetitre=bundle.getString("titre");
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.page_2,container,false);
        TextView pageTitle=rootView.findViewById(R.id.surahName);
        TextView surhText = rootView.findViewById(R.id.surahText);
        surhText.setText(json);
        pageTitle.setText(pagetitre);






        return rootView;
    }









}