package com.example.collatzcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BigInteger collatzNum;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch(item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_history:
                    selectedFragment = new HistoryFragment();
                    break;
                case R.id.nav_recent:
                    selectedFragment = new RecentFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, selectedFragment).commit();
            return true;
        }
    };

    public void calculate(View view) {
        TextView calc = (TextView) findViewById(R.id.chicken);

        collatzNum = getCollatz();
        CollatzCalculator collatz = new CollatzCalculator(new BigInteger(collatzNum.toString()));
        ArrayList<BigInteger> collatzList = collatz.createCollatzList();

        if (collatzNum.equals(new BigInteger("0"))) {
            calc.setText("Enter Valid Number");
        } else {
            String display = "Total iterations: " + collatz.getIterationTotal();
            calc.setText(display);


            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(false);
            mLayoutManager = new LinearLayoutManager(this);

            mAdapter = new CollatzAdapter(collatzList);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }


    public BigInteger getCollatz() {

        EditText inputNum = (EditText) findViewById(R.id.input_edit_text);
        if (inputNum.getText().toString().equals("")) {
            return new BigInteger("0");
        } else {
            String value = inputNum.getText().toString();
            return new BigInteger(value);
        }

    }

   /* public boolean isEven() {
        return temp % 2 == 0;
    }

    public int sequenceAmount() {
        ArrayList<Long> list = new ArrayList<Long>();
        return collatzList(list).size() - 1;
    }*/

    /*public ArrayList<Long> collatzList(ArrayList<Long> list) {
        temp = collatzNum;

        list.add(temp);
        while (temp != 1) {

            if (isEven()) {

                temp = temp / 2;
            } else {

                temp = temp * 3 + 1;
            }
            list.add(temp);
        }
        listCollatzIterations = list;
        return list;

    }*/

    /*public void collatzPrime(ArrayList<Long> list2) {
        temp = collatzNum;


        while (temp != 1) {

            if (isEven()) {

                temp = temp / 2;
            } else {
                String s = String.valueOf(temp);

                if (temp % 5 != 0) {
                    list2.add(temp);
                }

                temp = temp * 3 + 1;

            }

        }

        listPrime = list2;

    }*/

   /* public long highestPrime() {
        long max = 0;

        ArrayList<Long> list = new ArrayList<Long>();
        collatzList(list);

        for (int i = 0; i < list.size(); i++) {

        }

        return max;
    }*/
}