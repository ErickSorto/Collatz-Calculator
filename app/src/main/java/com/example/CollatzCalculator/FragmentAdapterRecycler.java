package com.example.CollatzCalculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
            case 3:
                return new ChartFragment();

        }

        return new IterationFragment();

    }

    @Override
    public int getItemCount() {
        return 4;
    }




}
