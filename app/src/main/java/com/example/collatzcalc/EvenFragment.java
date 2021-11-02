package com.example.collatzcalc;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.math.BigInteger;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EvenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EvenFragment extends Fragment {


    CollatzViewModel collatzViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;



    private View evenFragment;

    public EvenFragment() {
        // Required empty public constructor

    }


    public static EvenFragment newInstance() {
        EvenFragment fragment = new EvenFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        evenFragment = inflater.inflate(R.layout.fragment_even, container, false);
        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);

        mRecyclerView = (RecyclerView) evenFragment .findViewById(R.id.recycler_view_even);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CollatzAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);


        collatzViewModel.getCollatz().observe(getViewLifecycleOwner(),(list)->{
            mAdapter.setList(list);
            mAdapter.notifyDataSetChanged();

        });

        mRecyclerView.setAdapter(mAdapter);
        return evenFragment;
    }



}