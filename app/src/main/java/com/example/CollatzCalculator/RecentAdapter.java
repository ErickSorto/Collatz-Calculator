package com.example.CollatzCalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder>{
    private Context mContext;
    private ArrayList<BigInteger> mRecentList;
    private final RecyclerViewInterface mRecyclerViewInterface;

    public RecentAdapter(Context context, ArrayList<BigInteger> recentList, RecyclerViewInterface recyclerViewInterface){
        mContext = context;
        mRecentList = recentList;
        mRecyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recent_row, parent, false);
        return new RecentViewHolder(v, mRecyclerViewInterface);
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


    public static class RecentViewHolder extends RecyclerView.ViewHolder{
        public TextView mNumEnteredText;
        public RecentViewHolder(@NonNull View itemView, RecyclerViewInterface mRecyclerViewInterface) {
            super(itemView);
            mNumEnteredText = itemView.findViewById(R.id.recent_num_text_view);

            mNumEnteredText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mRecyclerViewInterface != null){
                        int position = getAbsoluteAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mRecyclerViewInterface.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
}
