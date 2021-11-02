package com.example.collatzcalc;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.math.BigInteger;

public class FragmentAdapterRecycler extends FragmentStateAdapter {




    public FragmentAdapterRecycler(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new EvenFragment();
            case 2:
                return new OddFragment();

        }

        return new IterationFragment();

    }

    @Override
    public int getItemCount() {
        return 3;
    }




}
