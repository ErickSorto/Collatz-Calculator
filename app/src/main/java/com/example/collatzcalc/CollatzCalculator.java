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
private ArrayList<BigInteger> sortedIterations = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedEven = new ArrayList<BigInteger>();
private ArrayList<BigInteger> sortedOdd = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedIterations = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedEven = new ArrayList<BigInteger>();
private ArrayList<BigInteger> reversedOdd = new ArrayList<BigInteger>();

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

        setSortedIterations(sortList((ArrayList<BigInteger>) collatzList.clone()));
        setSortedEven(sortList((ArrayList<BigInteger>) evenList.clone()));
        setSortedOdd(sortList((ArrayList<BigInteger>) oddList.clone()));
        setReversedEven(getReverseList((ArrayList<BigInteger>) evenList.clone()));
        setReversedIterations(getReverseList((ArrayList<BigInteger>) collatzList.clone()));
        setReversedOdd(getReverseList(getReverseList((ArrayList<BigInteger>) oddList.clone())));



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




    public BigInteger getNumEntered() {
        return numEntered;
    }

    public void setNumEntered(BigInteger numEntered) {
        this.numEntered = numEntered;
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
