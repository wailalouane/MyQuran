package com.example.myquran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.entities.model.SurahModel;

import java.util.ArrayList;
import java.util.List;

class RecycleViewHistAdapter extends RecyclerView.Adapter<RecycleViewHistAdapter.MyViewHolder> {
    List<SurahModel> historiqueList;
    Context mContext;

    public RecycleViewHistAdapter(ArrayList<SurahModel> surahListHis,Context context) {
        this.historiqueList=surahListHis;
        this.mContext=context;

    }

    @NonNull
    @Override
    public RecycleViewHistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.historique_element,parent,false);
        MyViewHolder holder=new MyViewHolder(view);





        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHistAdapter.MyViewHolder holder, int position) {
        holder.surahname.setText(historiqueList.get(position).getNameSurahArabe());
        holder.pageNum.setText(" : "+ String.valueOf(historiqueList.get(position).getStartSurahPage()));
    }

    @Override
    public int getItemCount() {
        return historiqueList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView surahname;
        TextView pageNum;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            surahname=itemView.findViewById(R.id.surahNameHis);
            pageNum=itemView.findViewById(R.id.pageNumHis);

        }
    }

}
