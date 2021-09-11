package com.example.collatzcalc;

import java.math.BigInteger;
import java.util.ArrayList;

public class CollatzCalculator {
private BigInteger numEntered;
private int iterationTotal = 0;


    public CollatzCalculator(BigInteger numEntered) {
        this.numEntered = numEntered;
    }

    private boolean isOdd(BigInteger val){
        return !val.mod(new BigInteger("2")).equals(BigInteger.ZERO);
    }



    public ArrayList<BigInteger> bigIntList(){
        ArrayList<BigInteger> bigIntList= new ArrayList<BigInteger>();

        bigIntList.add(numEntered);
        while (!numEntered.equals(new BigInteger("1"))) {

            if (isOdd(numEntered)) {
                numEntered = numEntered.multiply(new BigInteger("3")).add(new BigInteger("1"));

            } else {
                numEntered = numEntered.divide(new BigInteger("2"));
            }
            bigIntList.add(numEntered);
        }
        iterationTotal = bigIntList.size();
        return bigIntList;
    }

    public int getIterationTotal() {
        return iterationTotal;
    }

    public BigInteger getNumEntered() {
        return numEntered;
    }

    public void setNumEntered(BigInteger numEntered) {
        this.numEntered = numEntered;
    }
}
