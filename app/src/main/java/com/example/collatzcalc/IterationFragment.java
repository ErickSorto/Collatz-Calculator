package com.example.collatzcalc;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IterationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IterationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CollatzAdapter mAdapter;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CollatzViewModel collatzViewModel;
    private View iterationFragment;

    public IterationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IterationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IterationFragment newInstance(String param1, String param2) {
        IterationFragment fragment = new IterationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        iterationFragment = inflater.inflate(R.layout.fragment_iteration, container, false);
        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);

        mRecyclerView = (RecyclerView) iterationFragment.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new CollatzAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        collatzViewModel.getCollatz().observe(getViewLifecycleOwner(),(list)->{

            mAdapter.setList(list);
            mAdapter.notifyDataSetChanged();
        });


        return iterationFragment;
    }
}