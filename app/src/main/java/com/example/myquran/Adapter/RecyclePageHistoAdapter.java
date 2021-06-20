package com.example.myquran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.R;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclePageHistoAdapter extends RecyclerView.Adapter<RecyclePageHistoAdapter.MyViewHolder> {

    List<PageModel> pageModelList=new ArrayList<>();
    Context mContext;

    public RecyclePageHistoAdapter(List<PageModel> pageModelList, Context context) {
        this.pageModelList = pageModelList;
        mContext = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_historique_pages_count,parent,false);
        RecyclePageHistoAdapter.MyViewHolder holder=new RecyclePageHistoAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclePageHistoAdapter.MyViewHolder holder, int position) {
        holder.pageNum.setText(String.valueOf(pageModelList.get(position).getPageNum()));
        int x=pageModelList.get(position).getPageNum();
        String surahName="";
        switch (x){
            case 9: case 8:
                surahName="سورة الملك";
                break;
            case 7: case 6:
                surahName= "سورة القلم";
                break;
            case 5: case 4:
                surahName="سورة الحاقة";
                break;
            case 3 : case 2:
                surahName="سورة المعارج";
                break;
            case 1: case 0:
                surahName="سورة نوح";
                break;

        }

        holder.surahTitle.setText(surahName);
        holder.cptSurah.setText(String.valueOf(pageModelList.get(position).getCpt()));
    }

    @Override
    public int getItemCount() {
        return pageModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cptSurah;
        TextView surahTitle;
        TextView pageNum;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            cptSurah=itemView.findViewById(R.id.cptSurah);
            surahTitle=itemView.findViewById(R.id.surahTitle);
            pageNum=itemView.findViewById(R.id.pageNum);

        }
    }
}
