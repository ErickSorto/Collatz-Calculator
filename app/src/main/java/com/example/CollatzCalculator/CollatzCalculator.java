package com.example.CollatzCalculator;

import android.util.Log;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class CollatzCalculator {
private BigInteger numEntered;
private BigInteger maximum;
private double residue;

private ArrayList<BigInteger> collatzList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> evenList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> oddList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedIterations = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedEven = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedOdd = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedIterations = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedEven = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedOdd = new ArrayList<BigInteger>();
private BigInteger divisor = new BigInteger("2");
private BigInteger oneBigInt = new BigInteger("1");
private BigInteger threeBigInt = new BigInteger("3");
private Boolean sortSwitch = false;

    public CollatzCalculator(BigInteger numEntered) {
        this.numEntered = numEntered;
    }

    private boolean isOdd(BigInteger val){
        return val.testBit(0);
    }

    public void createCollatzList(){
        BigInteger originalNum = numEntered;
        collatzList.add(numEntered);
        long startTime = System.nanoTime();

        while (!numEntered.equals(new BigInteger("1"))) {

            if (isOdd(numEntered)) {
                oddList.add(numEntered);
                numEntered = numEntered.multiply(threeBigInt).add(oneBigInt);

            } else {
                evenList.add(numEntered);
                numEntered = numEntered.shiftRight(1);
            }
            collatzList.add(numEntered);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        Log.v("HEREEE", "it took "+ (elapsedTimeInSecond) + " seconds");

        oddList.add(oneBigInt);
        oddList.remove(originalNum);
        evenList.remove(originalNum);
        setSortedIterations(sortList((ArrayList<BigInteger>) collatzList.clone()));
        setSortedEven(sortList((ArrayList<BigInteger>) evenList.clone()));
        setSortedOdd(sortList((ArrayList<BigInteger>) oddList.clone()));
        setReversedEven(getReverseList((ArrayList<BigInteger>) evenList.clone()));
        setReversedIterations(getReverseList((ArrayList<BigInteger>) collatzList.clone()));
        setReversedOdd(getReverseList((ArrayList<BigInteger>) oddList.clone()));
    }

    public ArrayList<ChartItem> getChartArray(){
        ArrayList<ChartItem> arrayChart = new ArrayList<ChartItem>();

        arrayChart.add(new ChartItem("Total Iteration: ", String.valueOf(getIterationTotal())));
        arrayChart.add(new ChartItem("Total Even: ", String.valueOf(getEvenTotal())));
        arrayChart.add(new ChartItem("Total Odd: ", String.valueOf(getOddTotal())));
        arrayChart.add(new ChartItem("Maximum: ", String.valueOf(getMax())));
        arrayChart.add(new ChartItem("Residue: ", String.valueOf(getResidue())));
        return arrayChart;
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
        maximum = Collections.max(getCollatzList());
        return maximum;
    }
    public double getResidue(){
        return (Math.pow(2,getEvenTotal()))/ (Math.pow(3,getOddTotal()) * getIterationTotal());
    }

    public int getIterationTotal() {
        int iterationTotal = 0;
        return iterationTotal = collatzList.size() - 1;
    }

    public int getEvenTotal(){
        return evenList.size();
    }

    public int getOddTotal(){
        return oddList.size();
    }

    public ArrayList<BigInteger> getReverseList(ArrayList<BigInteger> listToBeReversed){
        Collections.reverse(listToBeReversed);
        return listToBeReversed;
    }

    public ArrayList<BigInteger> sortList(ArrayList<BigInteger> listToBeSorted){
        Collections.sort(listToBeSorted);
        return listToBeSorted;
    }

    public ArrayList<BigInteger> getSortedIterations() {
        return sortedIterations;
    }

    public void setSortedIterations(ArrayList<BigInteger> sortedIterations) {
        this.sortedIterations = sortedIterations;
    }

    public ArrayList<BigInteger> getSortedEven() {
        return sortedEven;
    }

    public void setSortedEven(ArrayList<BigInteger> sortedEven) {
        this.sortedEven = sortedEven;
    }

    public ArrayList<BigInteger> getSortedOdd() {
        return sortedOdd;
    }

    public void setSortedOdd(ArrayList<BigInteger> sortedOdd) {
        this.sortedOdd = sortedOdd;
    }

    public ArrayList<BigInteger> getReversedIterations() {
        return reversedIterations;
    }

    public void setReversedIterations(ArrayList<BigInteger> reversedIterations) {
        this.reversedIterations = reversedIterations;
    }

    public ArrayList<BigInteger> getReversedEven() {
        return reversedEven;
    }

    public void setReversedEven(ArrayList<BigInteger> reversedEven) {
        this.reversedEven = reversedEven;
    }

    public ArrayList<BigInteger> getReversedOdd() {
        return reversedOdd;
    }

    public void setReversedOdd(ArrayList<BigInteger> reversedOdd) {
        this.reversedOdd = reversedOdd;
    }
}
