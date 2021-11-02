package com.example.collatzcalc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
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

    private TextView numDisplay;
    private TextView numDisplayTitle;
    CollatzViewModel collatzViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);



        tabLayout = myFragment.findViewById(R.id.tab_layout);
        pager2 = myFragment.findViewById(R.id.view_pager2);
        Button calculate = myFragment.findViewById(R.id.calculate_button);
        ImageButton flip = myFragment.findViewById(R.id.flip_image_button);
        ImageButton sort = myFragment.findViewById(R.id.sort_image_button);

        FragmentManager fm = getFragmentManager();
        adapter = new FragmentAdapterRecycler(fm, getLifecycle());
        pager2.setAdapter(adapter);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                numDisplay = (TextView) myFragment.findViewById(R.id.numDisplay);
                numDisplayTitle = (TextView) myFragment.findViewById(R.id.numDisplayTitle);
                TextView title = (TextView) myFragment.findViewById(R.id.title_text_view);
                collatzNum = getCollatz();

                if (collatzNum.equals(new BigInteger("0"))) {
                    numDisplayTitle.setVisibility(View.GONE);
                    numDisplay.setText("Invalid");
                } else {
                    tabLoaded1 = false;
                    tabLoaded2 = false;
                    tabLoaded3 = false;
                    collatz = new CollatzCalculator(new BigInteger(collatzNum.toString()));
                    ArrayList<BigInteger> collatzList = collatz.createCollatzList();

                    tabLayout.setVisibility(View.VISIBLE);
                    pager2.setVisibility(View.VISIBLE);
                    numDisplayTitle.setVisibility(View.VISIBLE);
                    title.setVisibility(View.GONE);
                    FragmentManager fm = getFragmentManager();
                    adapter = new FragmentAdapterRecycler(fm, getLifecycle());
                    pager2.setAdapter(adapter);

                }

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                numDisplay = (TextView) myFragment.findViewById(R.id.numDisplay);
                fillFragment(position);
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
            String iterations = String.valueOf(collatz.getIterationTotal());
            String numTitle = "Total Iteration";
            numDisplay.setText(iterations);
            numDisplayTitle.setText(numTitle);

            if (tabLoaded1 == false) {
                collatzViewModel.getCollatz().setValue(collatz.getCollatzList());
               Log.v("tag","Im heree");
                tabLoaded1 = true;
            }
        }
        if (position == 1 && isChosen) {
            String evenTotal = String.valueOf(collatz.getEvenTotal());
            String numTitle = "Total Even";
            numDisplay.setText(evenTotal);
            numDisplayTitle.setText(numTitle);

            if (tabLoaded2 == false) {

                collatzViewModel.getCollatzEven().setValue(collatz.getEvenList());
                tabLoaded2 = true;
            }

        }
        if (position == 2 && isChosen) {
            String oddTotal = String.valueOf(collatz.getOddTotal());
            String numTitle = "Total Odd";
            numDisplay.setText(oddTotal);
            numDisplayTitle.setText(numTitle);

            if (tabLoaded3 == false) {
                collatzViewModel.getCollatzOdd().setValue(collatz.getOddList());
                tabLoaded3 = true;
            }

        }


    }

}
