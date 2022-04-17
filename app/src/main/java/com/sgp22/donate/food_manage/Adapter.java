package com.sgp22.donate.food_manage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Data_rest> data;

    Adapter(List<Data_rest> data){
        this.data=data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind the text view data recevied

        holder.img.setImageResource(data.get(position).getImage());
        holder.header.setText(data.get(position).getHeader());
        holder.desc.setText(data.get(position).getDesc());
        holder.address.setText(data.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView header,desc,address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageview1);
            header = itemView.findViewById(R.id.textView1);
            desc = itemView.findViewById(R.id.textView3);
            address = itemView.findViewById(R.id.textView2);
        }
    }
}
