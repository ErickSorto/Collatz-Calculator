package com.example.collatzcalc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollatzAdapter extends RecyclerView.Adapter<CollatzAdapter.ViewHolder> {

    ArrayList<Long> list;

    public CollatzAdapter(ArrayList<Long> list) {
        this.list = list;
    }

    @NonNull
    @Override




    public CollatzAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollatzAdapter.ViewHolder holder, int position) {
        holder.mNumber.setText(position + ": " + list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNumber = itemView.findViewById(R.id.iteration_num);
        }
    }
}
