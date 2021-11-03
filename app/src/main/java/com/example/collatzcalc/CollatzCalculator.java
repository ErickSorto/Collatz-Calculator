package com.example.collatzcalc;

import android.icu.text.Transliterator;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class CollatzCalculator {
private BigInteger numEntered;
private BigInteger biggestInt;
private ArrayList<BigInteger> collatzList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> evenList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> oddList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reverseList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortAndRevert = new ArrayList<BigInteger>();
private Boolean sortSwitch = false;


    public CollatzCalculator(BigInteger numEntered) {

        this.numEntered = numEntered;

    }

    private boolean isOdd(BigInteger val){
        return !val.mod(new BigInteger("2")).equals(BigInteger.ZERO);
    }




    public void createCollatzList(){

        collatzList.add(numEntered);
        while (!numEntered.equals(new BigInteger("1"))) {

            if (isOdd(numEntered)) {
                oddList.add(numEntered);
                numEntered = numEntered.multiply(new BigInteger("3")).add(new BigInteger("1"));

            } else {
                evenList.add(numEntered);
                numEntered = numEntered.divide(new BigInteger("2"));
            }
            collatzList.add(numEntered);
        }
    }

    public ArrayList<BigInteger> getCollatzList() {
        return collatzList;
    }

    public ArrayList<BigInteger> getEvenList() {
        return evenList;
    }

    public ArrayList<BigInteger> getOddList() {
        return oddList;
    }

    public BigInteger getMax(){

        biggestInt = Collections.max(getCollatzList());

        return biggestInt;
    }

    public int getIterationTotal() {
        int iterationTotal = 0;
        return iterationTotal = collatzList.size() - 1;
    }

    public int getEvenTotal(){
        int even = 0;
        return even = evenList.size();
    }

    public int getOddTotal(){
        int odd = 0;
        return odd = oddList.size();
    }

    public ArrayList<BigInteger> getReverseList(ArrayList<BigInteger> listToBeReversed){
        Collections.reverse(listToBeReversed);
        return listToBeReversed;
    }

    public ArrayList<BigInteger> getListSortedOrReverted(ArrayList<BigInteger> listSortedOrReverted, Boolean sortSwitch, int position){

        if(position == 0){
            if(sortSwitch == false){


                Collections.sort(listSortedOrReverted);

            }
            else {
                return getCollatzList();
            }

        }
        else if(position == 1){
            if(sortSwitch == false){


                Collections.sort(listSortedOrReverted);

            }
            else {
                return getCollatzList();
            }

        }
        else if(position == 2){
            if(sortSwitch == false){


                Collections.sort(listSortedOrReverted);

            }
            else {
                return getCollatzList();
            }

        }

        return listSortedOrReverted;





    }



    public BigInteger getNumEntered() {
        return numEntered;
    }

    public void setNumEntered(BigInteger numEntered) {
        this.numEntered = numEntered;
    }
}
