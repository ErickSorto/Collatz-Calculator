package com.example.collatzcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

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


        if (collatzNum.equals(new BigInteger("0"))) {
            calc.setText("Enter Valid Number");
        } else {
            CollatzCalculator collatz = new CollatzCalculator(new BigInteger(collatzNum.toString()));
            ArrayList<BigInteger> collatzList = collatz.createCollatzList();
            String display = "Total iterations: " + collatz.getIterationTotal();
            calc.setText(display);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.setVisibility(tabLayout.VISIBLE);

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
        if (inputNum.getText().toString().equals("") || inputNum.getText().toString().equals("0")||inputNum.getText().toString().equals("1")) {
            return new BigInteger("0");
        } else {
            String value = inputNum.getText().toString();
            return new BigInteger(value);
        }

    }

}