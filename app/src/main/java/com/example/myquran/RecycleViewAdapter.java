package com.example.myquran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.entities.model.SurahModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> implements Filterable {
    List<SurahModel> listsurah;
    List<SurahModel> listsurah1;

    Context mContext;

    private OnSurahListener mOnSurahListener;
    public RecycleViewAdapter(List<SurahModel> listsurah, Context context, OnSurahListener onSurahListener) {
        this.listsurah = listsurah;
        mContext = context;
        this.listsurah1 =new ArrayList<>();
        listsurah1.addAll(listsurah);
        this.mOnSurahListener=onSurahListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_line_list,parent,false);
        MyViewHolder holder =new MyViewHolder(view,mOnSurahListener);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.numSurah.setText(String.valueOf(listsurah.get(position).getIdSurah()));
        holder.nameSurah.setText(listsurah.get(position).getNameSurah());
        holder.pageSurah.setText(String.valueOf(listsurah.get(position).getStartSurahPage()));
        holder.nameSurahArabic.setText(listsurah.get(position).getNameSurahArabe());
    }

    @Override
    public int getItemCount() {
        return listsurah.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SurahModel> surahFilter =new ArrayList<>();
            if(constraint.toString().isEmpty()){
                surahFilter.addAll(listsurah1);
            }else{
                for(SurahModel surah :listsurah1){
                    if(surah.getNameSurah().toLowerCase().contains(constraint.toString().toLowerCase())){
                        surahFilter.add(surah);
                    }else if(surah.getNameSurahArabe().contains(constraint.toString())){
                        surahFilter.add(surah);
                    }

                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values=surahFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
             listsurah.clear();
             listsurah.addAll((Collection<? extends SurahModel>) results.values);
             notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView numSurah;
        TextView pageSurah;
        TextView nameSurah;
        TextView nameSurahArabic;
        OnSurahListener mOnSurahListener;

        public MyViewHolder(@NonNull View itemView,OnSurahListener onSurahListener) {
            super(itemView);
            this.mOnSurahListener=onSurahListener;
            numSurah =itemView.findViewById(R.id.numSurah);
            pageSurah=itemView.findViewById(R.id.pageSurah);
            nameSurah =itemView.findViewById(R.id.nameSurah);
            nameSurahArabic=itemView.findViewById(R.id.nameSurahArabic);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            mOnSurahListener.onSurahClick(getAdapterPosition());
        }
    }
    public interface OnSurahListener{
        void onSurahClick(int position);
    }

}
