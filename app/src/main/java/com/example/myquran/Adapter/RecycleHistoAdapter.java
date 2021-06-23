package com.example.myquran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.R;
import com.example.myquran.entities.model.HistoriqueModel;

import java.util.ArrayList;
import java.util.List;

public class RecycleHistoAdapter extends RecyclerView.Adapter<RecycleHistoAdapter.MyViewHolder> {

    List<HistoriqueModel> mHistoriqueModels=new ArrayList<>();
    Context mContext;

    public RecycleHistoAdapter(List<HistoriqueModel> historiqueModels, Context context) {
        mHistoriqueModels = historiqueModels;
        mContext = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_historque_element,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecycleHistoAdapter.MyViewHolder holder, int position) {
        holder.pageNum.setText(String.valueOf(mHistoriqueModels.get(position).getSurahPage()));
        holder.surahName.setText(mHistoriqueModels.get(position).getSurahName());
    }

    @Override
    public int getItemCount() {
        return mHistoriqueModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView surahName;
        TextView pageNum;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            surahName=itemView.findViewById(R.id.surahName);
            pageNum=itemView.findViewById(R.id.pageNum);
        }
    }
}
