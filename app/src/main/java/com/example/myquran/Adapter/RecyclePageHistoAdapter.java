package com.example.myquran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
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
    OnPageHistListener mOnPageHistListener;
    public RecyclePageHistoAdapter(List<PageModel> pageModelList, Context context,OnPageHistListener onPageHistListener) {
        this.pageModelList = pageModelList;
        mContext = context;
        this.mOnPageHistListener=onPageHistListener;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_historique_pages_count,parent,false);
        RecyclePageHistoAdapter.MyViewHolder holder=new RecyclePageHistoAdapter.MyViewHolder(view,mOnPageHistListener);
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
        int cpt100;
        cpt100=(pageModelList.get(position).getCpt()*100)/20;
        holder.mProgressBar.setProgress(cpt100);
    }

    @Override
    public int getItemCount() {
        return pageModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cptSurah;
        TextView surahTitle;
        TextView pageNum;
        ProgressBar mProgressBar;
        OnPageHistListener mOnPageHistListener;
        public MyViewHolder(@NonNull  View itemView,OnPageHistListener onPageHistListener) {
            super(itemView);
            this.mOnPageHistListener=onPageHistListener;
            cptSurah=itemView.findViewById(R.id.cptSurah);
            surahTitle=itemView.findViewById(R.id.surahTitle);
            pageNum=itemView.findViewById(R.id.pageNum);
            mProgressBar= itemView.findViewById(R.id.progress_circular);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnPageHistListener.onPageHistClick(getAdapterPosition());
        }
    }
    public interface OnPageHistListener{
        void onPageHistClick(int position);
    }
}
