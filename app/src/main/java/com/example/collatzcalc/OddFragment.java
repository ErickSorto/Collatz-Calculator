package com.example.collatzcalc;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigInteger;
import java.util.ArrayList;


public class OddFragment extends Fragment {



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View oddFragment;

    public OddFragment() {
    }


    public static OddFragment newInstance() {
        OddFragment fragment = new OddFragment();
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


        oddFragment = inflater.inflate(R.layout.fragment_odd, container, false);

        return oddFragment;
    }


}