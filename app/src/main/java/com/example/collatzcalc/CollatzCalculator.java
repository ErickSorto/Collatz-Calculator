package com.example.collatzcalc;

import java.math.BigInteger;
import java.util.ArrayList;

public class CollatzCalculator {
private BigInteger numEntered;
private BigInteger biggestInt = new BigInteger("0");
private ArrayList<BigInteger> collatzList;


    public CollatzCalculator(BigInteger numEntered) {
        this.numEntered = numEntered;
    }

    private boolean isOdd(BigInteger val){
        return !val.mod(new BigInteger("2")).equals(BigInteger.ZERO);
    }




    public ArrayList<BigInteger> createCollatzList(){

        collatzList.add(numEntered);
        while (!numEntered.equals(new BigInteger("1"))) {

            if (isOdd(numEntered)) {
                numEntered = numEntered.multiply(new BigInteger("3")).add(new BigInteger("1"));

            } else {
                numEntered = numEntered.divide(new BigInteger("2"));
            }
            collatzList.add(numEntered);
        }
        return collatzList;
    }

    public ArrayList<BigInteger> getCollatzList() {
        return collatzList;
    }

    public BigInteger getBiggestInt() {






            for (BigInteger integer : collatzList) {
                int res = integer.compareTo(biggestInt);
                if( res == 1 ){
                    biggestInt = integer;
                }
            }


        return biggestInt;
    }

    public int getIterationTotal() {
        int iterationTotal = 0;
        return iterationTotal = collatzList.size();
    }

    public BigInteger getNumEntered() {
        return numEntered;
    }

    public void setNumEntered(BigInteger numEntered) {
        this.numEntered = numEntered;
    }
}
