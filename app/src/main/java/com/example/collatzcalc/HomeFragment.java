package com.example.collatzcalc;

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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.math.BigInteger;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private BigInteger collatzNum;
    private CollatzCalculator collatz;
    boolean isChosen = false;
    boolean tabLoaded1 = false;
    boolean tabLoaded2 = false;
    boolean tabLoaded3 = false;


    View myFragment;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterRecycler adapter;

    private TextView calc;
    CollatzViewModel collatzViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);



        tabLayout = myFragment.findViewById(R.id.tab_layout);
        pager2 = myFragment.findViewById(R.id.view_pager2);
        Button calculate = myFragment.findViewById(R.id.calculate_button);

        FragmentManager fm = getFragmentManager();
        adapter = new FragmentAdapterRecycler(fm, getLifecycle());
        pager2.setAdapter(adapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView calc = (TextView) myFragment.findViewById(R.id.numDisplay);

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

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                calc = (TextView) myFragment.findViewById(R.id.numDisplay);
                fillFragment(pager2.getCurrentItem());
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });


        return myFragment;
    }

    public BigInteger getCollatz() {

        EditText inputNum = (EditText) myFragment.findViewById(R.id.input_edit_text);
        if (inputNum.getText().toString().equals("") || inputNum.getText().toString().equals("0") || inputNum.getText().toString().equals("1")) {
            isChosen = false;
            return new BigInteger("0");
        } else {
            String value = inputNum.getText().toString();
            isChosen = true;
            return new BigInteger(value);
        }

    }


    public void fillFragment(int position) {

        if (position == 0 && isChosen) {
            String iterations = "Total Iterations: " + collatz.getIterationTotal();
            calc.setText(iterations);
            if (tabLoaded1 == false) {
                collatzViewModel.getCollatz().setValue(collatz.getCollatzList());
                Log.v("sd",collatz.getCollatzList().toString());
                tabLoaded1 = true;
            }
        }
        if (position == 1 && isChosen) {
            String evenTotal = "Total Even: " + collatz.getEvenTotal();
            calc.setText(evenTotal);

            if (tabLoaded2 == false) {

                collatzViewModel.getCollatz().setValue(collatz.getEvenList());
                tabLoaded2 = true;
            }

        }
        if (position == 2 && isChosen) {
            String oddTotal = "Total Odd: " + collatz.getOddTotal();
            calc.setText(oddTotal);
            if (tabLoaded3 == false) {
                collatzViewModel.getCollatz().setValue(collatz.getOddList());
                tabLoaded3 = true;
            }

        }


    }

}
