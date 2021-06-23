package com.example.myquran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.R;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclePlusLusAdapter extends RecyclerView.Adapter<RecyclePlusLusAdapter.MyViewHolder> {
    List<PageModel> pageModelList=new ArrayList<>();
    Context mContext;

    public RecyclePlusLusAdapter(List<PageModel> pageModelList, Context context) {
        this.pageModelList = pageModelList;
        this.mContext = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_plus_lus_page,parent,false);
        RecyclePlusLusAdapter.MyViewHolder holder=new RecyclePlusLusAdapter.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclePlusLusAdapter.MyViewHolder holder, int position) {
        holder.surahName.setText(pageModelList.get(position).getPageTitle());
        holder.pageNum.setText(String.valueOf(pageModelList.get(position).getPageNum()));
        holder.cpt.setText(String.valueOf(pageModelList.get(position).getCpt()));
        int cpt100;
        cpt100=(pageModelList.get(position).getCpt()*100)/20;
        holder.mProgressBar.setProgress(cpt100);

    }

    @Override
    public int getItemCount() {
        return pageModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView surahName;
        TextView pageNum;
        TextView cpt;
        ProgressBar mProgressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            surahName=itemView.findViewById(R.id.surahName);
            pageNum=itemView.findViewById(R.id.pageNum);
            cpt=itemView.findViewById(R.id.cptSurah);
            mProgressBar= itemView.findViewById(R.id.progress_circular);

        }
    }
}
