package com.example.collatzcalc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collatzcalc.R;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment  extends Fragment {
    private BigInteger collatzNum;
    private CollatzCalculator collatz;
    boolean isChosen = false;
    boolean tabLoaded1 = false;
    boolean tabLoaded2= false;
    boolean tabLoaded3 = false;

    View myFragment;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterRecycler adapter;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private TextView calc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = myFragment.findViewById(R.id.tab_layout);
        pager2 = myFragment.findViewById(R.id.view_pager2);
        Button calculate = myFragment.findViewById(R.id.calculate_button);


        FragmentManager fm = getFragmentManager();
        adapter = new FragmentAdapterRecycler(fm, getLifecycle());
        pager2.setAdapter(adapter);



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView calc = (TextView) myFragment.findViewById(R.id.chicken);

                collatzNum = getCollatz();

                if (collatzNum.equals(new BigInteger("0"))) {
                    calc.setText("Enter Valid Number");
                } else {
                    tabLoaded1 = false;
                    tabLoaded2 = false;
                    tabLoaded3 = false;
                    collatz = new CollatzCalculator(new BigInteger(collatzNum.toString()));
                    ArrayList<BigInteger> collatzList = collatz.createCollatzList();



                    tabLayout.setVisibility(View.VISIBLE);
                    pager2.setVisibility(View.VISIBLE);
                    FragmentManager fm = getFragmentManager();
                    adapter = new FragmentAdapterRecycler(fm, getLifecycle());
                    pager2.setAdapter(adapter);



                }




            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager2.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                calc = (TextView) myFragment.findViewById(R.id.chicken);
                tabLayout.selectTab(tabLayout.getTabAt(position));
                fillFragment(position);


            }
        });


        return myFragment;
    }

    public BigInteger getCollatz() {

        EditText inputNum = (EditText) myFragment.findViewById(R.id.input_edit_text);
        if (inputNum.getText().toString().equals("") || inputNum.getText().toString().equals("0")||inputNum.getText().toString().equals("1")) {
            isChosen = false;
            return new BigInteger("0");
        } else {
            String value = inputNum.getText().toString();
            isChosen = true;
            return new BigInteger(value);
        }

    }



    public void fillFragment(int position){

        if (position== 0 && isChosen){
            String iterations = "Total Iterations: " + collatz.getIterationTotal();
            calc.setText(iterations);
            if(tabLoaded1 == false){
                mRecyclerView = (RecyclerView) myFragment.findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(false);
                mLayoutManager = new LinearLayoutManager(getActivity());


                mAdapter = new CollatzAdapter(collatz.getCollatzList());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                tabLoaded1 = true;
            }
        }
        else if(position == 1 && isChosen) {
            String evenTotal = "Total Even: " + collatz.getEvenTotal();
            calc.setText(evenTotal);

            if (tabLoaded2 == false){
                mRecyclerView = (RecyclerView) myFragment.findViewById(R.id.recycler_view_even);
                mRecyclerView.setHasFixedSize(false);
                mLayoutManager = new LinearLayoutManager(getActivity());


                mAdapter = new CollatzAdapter(collatz.getEvenList());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);}
            tabLoaded2 = true;
        }
        else if( position == 2 && isChosen ){
            String oddTotal = "Total Odd: " + collatz.getOddTotal();
            calc.setText(oddTotal);
            if(tabLoaded3 == false){
                mRecyclerView = (RecyclerView) myFragment.findViewById(R.id.recycler_view_odd);
                mRecyclerView.setHasFixedSize(false);
                mLayoutManager = new LinearLayoutManager(getActivity());


                mAdapter = new CollatzAdapter(collatz.getOddList());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);}
            tabLoaded3 = true;
        }



    }
















}
