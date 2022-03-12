package com.example.collatzcalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collatzcalc.R;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;

public class RecentFragment extends Fragment {

    CollatzViewModel collatzViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecentAdapter mAdapter;
    private View recentFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recentFragment = inflater.inflate(R.layout.fragment_recent, container, false);

        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);

        ArrayList<BigInteger> reversedList = (ArrayList<BigInteger>) collatzViewModel.getRecentNumList().clone();

        Collections.reverse(reversedList);
        mRecyclerView = (RecyclerView) recentFragment.findViewById(R.id.recent_recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getActivity());




        mAdapter = new RecentAdapter(getActivity(),reversedList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return recentFragment;
    }
}
