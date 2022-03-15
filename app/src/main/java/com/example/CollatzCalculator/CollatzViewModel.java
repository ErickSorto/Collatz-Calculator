package com.example.CollatzCalculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigInteger;
import java.util.ArrayList;

public class CollatzViewModel extends ViewModel {
    private MutableLiveData<ArrayList<BigInteger>> collatz = new MutableLiveData();
    private MutableLiveData<ArrayList<BigInteger>> collatzEven = new MutableLiveData();
    private MutableLiveData<ArrayList<BigInteger>> collatzOdd = new MutableLiveData();
    private MutableLiveData<ArrayList<ChartItem>> chartItems = new MutableLiveData();
    private BigInteger recentNumClicked = BigInteger.ZERO;
    private ArrayList<BigInteger> recentNumList = new ArrayList<BigInteger>();

    public MutableLiveData<ArrayList<BigInteger>> getCollatz(){
        return collatz;
    }
    public MutableLiveData<ArrayList<BigInteger>> getCollatzEven(){
        return collatzEven;
    }
    public MutableLiveData<ArrayList<BigInteger>> getCollatzOdd(){
        return collatzOdd;
    }
    public MutableLiveData<ArrayList<ChartItem>> getChartItems(){
        return chartItems;
    }

    public ArrayList<BigInteger> getRecentNumList(){
        return recentNumList;
    }

    public BigInteger getRecentNumClicked() {
        return recentNumClicked;
    }

    public void setRecentNumClicked(BigInteger recentNumClicked) {
        this.recentNumClicked = recentNumClicked;
    }

    public void addRecent(BigInteger recentNum){
        if (recentNumList.contains(recentNum)) {
            recentNumList.remove(recentNum);
        }
        recentNumList.add(recentNum);
    }
}