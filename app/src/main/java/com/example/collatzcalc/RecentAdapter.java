package com.example.collatzcalc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder> {
    private Context mContext;
    private ArrayList<BigInteger> mRecentList;

    public RecentAdapter(Context context, ArrayList<BigInteger> recentList){
        mContext = context;
        mRecentList = recentList;

    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recent_row, parent, false);
        return new RecentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        BigInteger recentNum = mRecentList.get(position);
        String recentNumString = recentNum.toString();

        holder.mNumEnteredText.setText(recentNumString);
    }

    @Override
    public int getItemCount() {
        return mRecentList.size();
    }


    public class RecentViewHolder extends RecyclerView.ViewHolder{
        public TextView mNumEnteredText;
        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumEnteredText = itemView.findViewById(R.id.recent_num_text_view);
        }
    }
}
