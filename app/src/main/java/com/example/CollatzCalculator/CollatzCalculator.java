package com.example.CollatzCalculator;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class CollatzCalculator {
private BigInteger numEntered;
private BigInteger maximum;
private double residue;
private double elapsedTimeInSecond;

private ArrayList<BigInteger> collatzList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> evenList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> oddList = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedIterations = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedEven = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedOdd = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedIterations = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedEven = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedOdd = new ArrayList<BigInteger>();
private BigInteger oneBigInt = new BigInteger("1");
private BigInteger threeBigInt = new BigInteger("3");

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

        while (numEntered.bitLength() > 1) {

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

        elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;


        oddList.add(oneBigInt);
        oddList.remove(originalNum);
        evenList.remove(originalNum);

    }

    public ArrayList<ChartItem> getChartArray(){
        ArrayList<ChartItem> arrayChart = new ArrayList<ChartItem>();

        arrayChart.add(new ChartItem("Total Iteration: ", String.valueOf(getIterationTotal())));
        arrayChart.add(new ChartItem("Total Even: ", String.valueOf(getEvenTotal())));
        arrayChart.add(new ChartItem("Total Odd: ", String.valueOf(getOddTotal())));
        arrayChart.add(new ChartItem("Even Percentage: ", getEvenPercentage()));
        arrayChart.add(new ChartItem("Odd Percentage: ", getOddPercentage()));
        arrayChart.add(new ChartItem("Calculation time: ", String.format("%.3f", getIterationTime()) + " seconds"));
        arrayChart.add(new ChartItem("Residue: ", String.valueOf(getResidue())));
        arrayChart.add(new ChartItem("Maximum: ", String.valueOf(getMax())));
        return arrayChart;
    }

    public String getEvenPercentage(){
        double percent = ((double) getEvenTotal())/ ((double) getIterationTotal()) * 100;
        String formattedPercent = String.format("%.2f", percent);
        String percentage = formattedPercent + "%";

        return percentage;
    }

    public String getOddPercentage(){
        double percent = ((double) getOddTotal())/ ((double) getIterationTotal()) * 100;
        String formattedPercent = String.format("%.2f", percent);
        String percentage = formattedPercent + "%";

        return percentage;
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
        return (Math.pow(2,getEvenTotal()))/ (Math.pow(3,getOddTotal()) * (1 / getIterationTotal()));
    }

    public int getIterationTotal() {
        return collatzList.size() - 1;
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
        if (sortedIterations.isEmpty()){
            sortedIterations = sortList((ArrayList<BigInteger>) collatzList.clone());
        }

        return sortedIterations;
    }

    public ArrayList<BigInteger> getSortedEven() {
        if (sortedEven.isEmpty()){
            sortedEven = sortList((ArrayList<BigInteger>) evenList.clone());
        }
        return sortedEven;
    }

    public ArrayList<BigInteger> getSortedOdd() {
        if(sortedOdd.isEmpty()){
           sortedOdd = sortList((ArrayList<BigInteger>) oddList.clone());
        }
        return sortedOdd;
    }

    public ArrayList<BigInteger> getReversedIterations() {
        return getReverseList((ArrayList<BigInteger>) collatzList.clone());
    }

    public ArrayList<BigInteger> getReversedEven() {
        return getReverseList((ArrayList<BigInteger>) evenList.clone());
    }

    public ArrayList<BigInteger> getReversedOdd() {
        return getReverseList((ArrayList<BigInteger>) oddList.clone());
    }

    public Double getIterationTime(){
        return elapsedTimeInSecond;
    }

}