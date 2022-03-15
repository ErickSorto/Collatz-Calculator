package com.example.collatzcalc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CollatzAdapter extends RecyclerView.Adapter<CollatzAdapter.ViewHolder> {

    ArrayList<BigInteger> list = new ArrayList<BigInteger>();
    List<Long> primeList;

    public CollatzAdapter() {
    }

    public CollatzAdapter(ArrayList<BigInteger> list, ArrayList<Long> primeList) {
        this.list = list;
        this.primeList = primeList;
    }
    public CollatzAdapter(ArrayList<BigInteger> list) {
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

        holder.mIterationPosition.setText((position + ":"));
        holder.mNumber.setText(list.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNumber;
        public TextView mIterationPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNumber = itemView.findViewById(R.id.iteration_num);
            mIterationPosition = itemView.findViewById(R.id.iteration_position);

        }

    }

    public void setList(ArrayList<BigInteger> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
}