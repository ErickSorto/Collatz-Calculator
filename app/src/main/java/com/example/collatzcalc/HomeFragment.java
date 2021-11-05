package com.example.collatzcalc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {
    private BigInteger collatzNum;
    private CollatzCalculator collatz;
    boolean isChosen = false;
    boolean tabLoaded1 = false;
    boolean tabLoaded2 = false;
    boolean tabLoaded3 = false;
    boolean sortSwitchTab1 = false;
    boolean sortSwitchTab2 = false;
    boolean sortSwitchTab3 = false;
    boolean reverseSwitchTab1 = false;
    boolean reverseSwitchTab2 = false;
    boolean reverseSwitchTab3 = false;



    View myFragment;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterRecycler adapter;

    private TextView numDisplay;
    private TextView numDisplayTitle;
    private SwitchCompat isSorted;
    private SwitchCompat isRevered;

    CollatzViewModel collatzViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        collatzViewModel = new ViewModelProvider(getActivity()).get(CollatzViewModel.class);


        tabLayout = myFragment.findViewById(R.id.tab_layout);
        pager2 = myFragment.findViewById(R.id.view_pager2);
        isSorted = myFragment.findViewById(R.id.switch_sort);
        isRevered = myFragment.findViewById(R.id.switch_reverse);

        Button calculate = myFragment.findViewById(R.id.calculate_button);
        ImageButton flip = myFragment.findViewById(R.id.flip_image_button);
        ImageButton sort = myFragment.findViewById(R.id.sort_image_button);
        RelativeLayout displayButtonTotal = myFragment.findViewById(R.id.display_button_layout);

        FragmentManager fm = getFragmentManager();
        adapter = new FragmentAdapterRecycler(fm, getLifecycle());
        pager2.setAdapter(adapter);

        isSorted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                fillSortedFragment(pager2.getCurrentItem(), collatz, isChecked);



            }
        });

        isRevered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        sort.setOnClickListener(new View.OnClickListener() { //BUG: When new computation is made, all tab loaded are true so when sort button gets click all of pages get updated. WHEN BUTTON IS clicked again calcualtion are made again even if items are loaded
            @Override
            public void onClick(View v) {

                isSorted.toggle();


            }
        });

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRevered.toggle();
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
                    sortSwitchTab1 = false;
                    sortSwitchTab2 = false;
                    sortSwitchTab3 = false;
                    reverseSwitchTab1 = false;
                    reverseSwitchTab2 = false;
                    reverseSwitchTab3 = false;
                    collatz = new CollatzCalculator(new BigInteger(collatzNum.toString()));
                    collatz.createCollatzList();



                    tabLayout.setVisibility(View.VISIBLE);
                    pager2.setVisibility(View.VISIBLE);

                    displayButtonTotal.setVisibility(View.VISIBLE);
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

                if (isChosen == true) {
                    changeDisplay(position, collatz);
                    fillFragment(position, collatz);
                }

                tabLayout.selectTab(tabLayout.getTabAt(position));
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


    public void fillFragment(int position, CollatzCalculator collatz) {

        if (position == 0 && tabLoaded1 == false) {
            collatzViewModel.getCollatz().setValue(collatz.getCollatzList());

            tabLoaded1 = true;
        }
        else if (position == 1 && tabLoaded2 == false ) {

            collatzViewModel.getCollatzEven().setValue(collatz.getEvenList());
            tabLoaded2 = true;
        }
        else if (position == 2 && tabLoaded3 == false) {
            collatzViewModel.getCollatzOdd().setValue(collatz.getOddList());
            tabLoaded3 = true;
        }

    }

    public void fillSortedFragment(int position, CollatzCalculator collatz, Boolean isChecked){
        if(isChecked){
            if(position == 0){
                sortSwitchTab1 = true;
                if(reverseSwitchTab1){
                    collatzViewModel.getCollatz().setValue(collatz.getReverseList((ArrayList<BigInteger>) collatz.getSortedIterations().clone()));
                }
                else{
                    collatzViewModel.getCollatz().setValue(collatz.getSortedIterations());
                }
            }

            if(position == 1){
                sortSwitchTab2 = true;
                if(reverseSwitchTab2){
                    collatzViewModel.getCollatzEven().setValue(collatz.getReverseList((ArrayList<BigInteger>) collatz.getSortedEven().clone()));
                }
                else {
                    collatzViewModel.getCollatzEven().setValue(collatz.getSortedEven());
                }
            }

            if(position == 2){
                sortSwitchTab3 = true;
                if(reverseSwitchTab3){
                    collatzViewModel.getCollatzOdd().setValue(collatz.getReverseList((ArrayList<BigInteger>) collatz.getSortedOdd().clone()));
                }
                else {
                    collatzViewModel.getCollatzOdd().setValue(collatz.getSortedOdd());
                }
            }
        }
        else if(isChecked == false){
            if (position == 0){
                sortSwitchTab1 = false;
                if(reverseSwitchTab1){
                    collatzViewModel.getCollatz().setValue(collatz.getReversedIterations());
                }
                else{
                    collatzViewModel.getCollatz().setValue(collatz.getCollatzList());
                }

            }
            else if(position == 1){
                sortSwitchTab2 = false;
                if(reverseSwitchTab2){
                    collatzViewModel.getCollatzEven().setValue(collatz.getReversedEven());
                }
                else{
                    collatzViewModel.getCollatzEven().setValue(collatz.getEvenList());
                }

            }
            else if(position == 2 ){
                sortSwitchTab3 = false;
                if(reverseSwitchTab3){
                    collatzViewModel.getCollatzOdd().setValue(collatz.getReversedEven());
                }
                else {
                    collatzViewModel.getCollatzOdd().setValue(collatz.getOddList());
                }

            }
        }

    }

    public void fillReversedFragment(int position, CollatzCalculator collatz){
        if(position == 0){
            if(sortSwitchTab1 == false){
                collatzViewModel.getCollatz().setValue(collatz.getReverseList(collatz.getCollatzList()));
            }

        }
        else if(position == 1){
            if(sortSwitchTab2 == false){
                collatzViewModel.getCollatzEven().setValue(collatz.getReverseList(collatz.getEvenList()));
            }

        }
        else if(position == 2){
            if(sortSwitchTab3 == false){
                collatzViewModel.getCollatzOdd().setValue(collatz.getReverseList(collatz.getOddList()));
            }


        }
    }

    public void changeDisplay(int position, CollatzCalculator collatz){

        if (position == 0 && isChosen) {
            String iterations = String.valueOf(collatz.getIterationTotal());
            String numTitle = "Total Iteration";
            numDisplay.setText(iterations);
            numDisplayTitle.setText(numTitle);

            if(isSorted.isChecked() != sortSwitchTab1){
                isSorted.toggle();
            }
        }
        else if(position == 1 && isChosen){
            String evenTotal = String.valueOf(collatz.getEvenTotal());
            String numTitle = "Total Even";
            numDisplay.setText(evenTotal);
            numDisplayTitle.setText(numTitle);
            if(isSorted.isChecked() != sortSwitchTab2) {
                isSorted.toggle();
            }
        }
        else if(position == 2 && isChosen){
            String oddTotal = String.valueOf(collatz.getOddTotal());
            String numTitle = "Total Odd";
            numDisplay.setText(oddTotal);
            numDisplayTitle.setText(numTitle);
            if(isSorted.isChecked() != sortSwitchTab3){
                isSorted.toggle();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}


