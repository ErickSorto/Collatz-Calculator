package com.example.CollatzCalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    ArrayList<ChartItem> list = new ArrayList<ChartItem>();

    public ChartAdapter(){}
    public ChartAdapter(ArrayList<ChartItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartAdapter.ViewHolder holder, int position) {
        holder.chartSectionTitle.setText(list.get(position).getSectionName());
        holder.chartSectionInfo.setText(list.get(position).getSectionInformation());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView chartSectionTitle;
        public TextView chartSectionInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chartSectionTitle = itemView.findViewById(R.id.chart_section_title);
            chartSectionInfo = itemView.findViewById(R.id.chart_section_info);
        }

    }

    public void setList(ArrayList<ChartItem> list) {
        this.list = list;
    }
}