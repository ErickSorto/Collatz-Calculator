package com.example.collatzcalc;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CollatzViewModel extends ViewModel {
    private MutableLiveData<ArrayList<BigInteger>> collatz = new MutableLiveData();
    private MutableLiveData<ArrayList<BigInteger>> collatzEven = new MutableLiveData();
    private MutableLiveData<ArrayList<BigInteger>> collatzOdd = new MutableLiveData();

    public MutableLiveData<ArrayList<BigInteger>> getCollatz(){
        return collatz;
    }
    public MutableLiveData<ArrayList<BigInteger>> getCollatzEven(){
        return collatzEven;
    }
    public MutableLiveData<ArrayList<BigInteger>> getCollatzOdd(){
        return collatzOdd;
    }
}
