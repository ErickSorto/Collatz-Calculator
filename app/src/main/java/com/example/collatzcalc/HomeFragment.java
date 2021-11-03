package com.example.collatzcalc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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
    boolean sortSwitchTab1 = false;
    boolean sortSwitchTab2 = false;
    boolean sortSwitchTab3 = false;



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
        RelativeLayout displayButtonTotal = myFragment.findViewById(R.id.display_button_layout);

        FragmentManager fm = getFragmentManager();
        adapter = new FragmentAdapterRecycler(fm, getLifecycle());
        pager2.setAdapter(adapter);

        sort.setOnClickListener(new View.OnClickListener() { //BUG: When new computation is made, all tab loaded are true so when sort button gets click all of pages get updated. WHEN BUTTON IS clicked again calcualtion are made again even if items are loaded
            @Override
            public void onClick(View v) {

                fillSortedFragment(pager2.getCurrentItem(), collatz);



            }
        });

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillReversedFragment(pager2.getCurrentItem(), collatz);
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

    public void fillSortedFragment(int position, CollatzCalculator collatz){
        if(position ==0){

            collatzViewModel.getCollatz().setValue(collatz.getListSortedOrReverted((ArrayList<BigInteger>) collatz.getCollatzList().clone(), sortSwitchTab1, position));
            if(sortSwitchTab1 == false){
                sortSwitchTab1 = true;
            }
            else if(sortSwitchTab1){
                sortSwitchTab1 = false;
            }
        }
        else if(position == 1){
            collatzViewModel.getCollatzEven().setValue(collatz.getListSortedOrReverted((ArrayList<BigInteger>) collatz.getEvenList().clone(), sortSwitchTab2, position));
            if(sortSwitchTab2 == false){
                sortSwitchTab2 = true;
            }
            else if(sortSwitchTab2){
                sortSwitchTab2 = false;
            }
        }
        else if(position == 2){
            collatzViewModel.getCollatzOdd().setValue(collatz.getListSortedOrReverted((ArrayList<BigInteger>) collatz.getOddList().clone(), sortSwitchTab3, position));
            if(sortSwitchTab3 == false){
                sortSwitchTab3 = true;
            }
            else if(sortSwitchTab3){
                sortSwitchTab3 = false;
            }
        }
    }

    public void fillReversedFragment(int position, CollatzCalculator collatz){
        if(position == 0){
            collatzViewModel.getCollatz().setValue(collatz.getReverseList(collatz.getCollatzList()));
        }
        else if(position == 1){
            collatzViewModel.getCollatzEven().setValue(collatz.getReverseList(collatz.getEvenList()));
        }
        else if(position == 2){
            collatzViewModel.getCollatzOdd().setValue(collatz.getReverseList(collatz.getOddList()));
        }
    }

    public void changeDisplay(int position, CollatzCalculator collatz){

        if (position == 0 && isChosen) {
            String iterations = String.valueOf(collatz.getIterationTotal());
            String numTitle = "Total Iteration";
            numDisplay.setText(iterations);
            numDisplayTitle.setText(numTitle);
        }

        else if(position == 1 && isChosen){
            String evenTotal = String.valueOf(collatz.getEvenTotal());
            String numTitle = "Total Even";
            numDisplay.setText(evenTotal);
            numDisplayTitle.setText(numTitle);
        }
        else if(position == 2 && isChosen){
            String oddTotal = String.valueOf(collatz.getOddTotal());
            String numTitle = "Total Odd";
            numDisplay.setText(oddTotal);
            numDisplayTitle.setText(numTitle);
        }
    }


}


