package com.example.myquran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.entities.model.AyahModel;
import com.example.myquran.entities.model.PageModel;

import java.util.ArrayList;
import java.util.List;

class RecycleViewAdapterPages extends RecyclerView.Adapter<RecycleViewAdapterPages.MyViewHolder> {
    List<PageModel> pageList;
    Context mContext;


    public RecycleViewAdapterPages(ArrayList<PageModel> pageList,Context context) {
        this.mContext=context;
        this.pageList=pageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_page,parent,false);
        MyViewHolder holder =new MyViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pageNum.setText(String.valueOf(pageList.get(position).getPageNum()));
        holder.pageBody.setText(pageList.get(position).showPage());
        holder.surahName.setText(pageList.get(position).getPageTitle());


    }



    @Override
    public int getItemCount() {
        return pageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView surahName;
        TextView pageBody;
        TextView pageNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            surahName =itemView.findViewById(R.id.surahName);
            pageBody =itemView.findViewById(R.id.pageBody);
            pageNum=itemView.findViewById(R.id.pageNum);

        }
    }
}
