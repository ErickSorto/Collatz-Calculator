package com.example.collatzcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int input, iterationTotal, iterationMax;
    private long temp;
    private long collatzNum;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Long> listCollatzIterations = new ArrayList<Long>();
    ArrayList<Long> listPrime = new ArrayList<Long>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void calculate(View view) {
        TextView calc = (TextView) findViewById(R.id.chicken);
        collatzNum = getCollatz();
        if (collatzNum == 0) {
            calc.setText("Enter Valid Number");
        } else {
            calc.setText("Total iterations: " + String.valueOf(sequenceAmount()));


            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(false);
            mLayoutManager = new LinearLayoutManager(this);
            collatzPrime(listPrime);
            mAdapter = new CollatzAdapter(listCollatzIterations, listPrime);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }



    }


    public long getCollatz() {

        EditText inputNum = (EditText) findViewById(R.id.input_edit_text);
        if (inputNum.getText().toString().equals("")) {
            return 0;
        } else {
            String value = inputNum.getText().toString();
            return Long.parseLong(value);
        }

    }

    public boolean isEven() {
        return temp % 2 == 0;
    }

    public int sequenceAmount() {
        ArrayList<Long> list = new ArrayList<Long>();
        return collatzList(list).size() - 1;
    }

    public ArrayList<Long> collatzList(ArrayList<Long> list) {
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

    }

    public void collatzPrime(ArrayList<Long> list2) {
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

    }

    public long highestPrime() {
        long max = 0;

        ArrayList<Long> list = new ArrayList<Long>();
        collatzList(list);

        for (int i = 0; i < list.size(); i++) {

        }

        return max;
    }
}