package com.example.CollatzCalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChartFragment extends Fragment {
    CollatzViewModel collatzViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChartAdapter mAdapter;
    private View chartFragment;

    public ChartFragment() {
        // Required empty public constructor

    }

    public static ChartFragment newInstance() {
        ChartFragment fragment = new ChartFragment();
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
        chartFragment = inflater.inflate(R.layout.fragment_chart, container, false);
        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);

        mRecyclerView = (RecyclerView) chartFragment.findViewById(R.id.recycler_view_chart);
        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ChartAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);

        collatzViewModel.getChartItems().observe(getViewLifecycleOwner(),(list)->{
            mAdapter.setList(list);
            mAdapter.notifyDataSetChanged();

        });

        mRecyclerView.setAdapter(mAdapter);

        return chartFragment;
    }
}