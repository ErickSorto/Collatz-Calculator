package com.example.collatzcalc;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CollatzViewModel extends ViewModel {
    private MutableLiveData<ArrayList<BigInteger>> collatz = new MutableLiveData();
    public MutableLiveData<ArrayList<BigInteger>> getCollatz(){
        return collatz;
    }
}
