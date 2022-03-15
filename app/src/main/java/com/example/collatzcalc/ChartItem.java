package com.example.collatzcalc;

public class ChartItem {
    private String sectionName;
    private String sectionInformation;

    public ChartItem(String sectionName,  String sectionInformation) {
        this.sectionName = sectionName;
        this.sectionInformation = sectionInformation;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionInformation() {
        return sectionInformation;
    }

    public void setSectionInformation(String sectionInformation) {
        this.sectionInformation = sectionInformation;
    }
}