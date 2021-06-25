package com.example.myquran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.entities.model.HistoriqueModel;
import com.example.myquran.entities.model.SurahModel;

import java.util.ArrayList;
import java.util.List;

class RecycleViewHistAdapter extends RecyclerView.Adapter<RecycleViewHistAdapter.MyViewHolder> {
    List<HistoriqueModel> historiqueList;
    Context mContext;
    private OnHistListener mOnHistListener;


    public RecycleViewHistAdapter(ArrayList<HistoriqueModel> surahListHis,Context context,OnHistListener onHistListener) {
        this.historiqueList=surahListHis;
        this.mContext=context;
        this.mOnHistListener=onHistListener;

    }

    @NonNull
    @Override
    public RecycleViewHistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.historique_element,parent,false);
        MyViewHolder holder=new MyViewHolder(view,mOnHistListener);





        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHistAdapter.MyViewHolder holder, int position) {
        holder.surahname.setText(historiqueList.get(position).getSurahName());
        holder.pageNum.setText(String.valueOf(historiqueList.get(position).getSurahPage())+"  :  ");
    }

    @Override
    public int getItemCount() {
        return historiqueList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView surahname;
        TextView pageNum;
        OnHistListener mOnHistListener;

        public MyViewHolder(@NonNull View itemView,OnHistListener onHistListener) {
            super(itemView);
            surahname=itemView.findViewById(R.id.surahNameHis);
            pageNum=itemView.findViewById(R.id.pageNumHis);
            this.mOnHistListener=onHistListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnHistListener.onHistClick(getAdapterPosition());
        }
    }

    public interface OnHistListener{
        void onHistClick(int position);
    }

}
