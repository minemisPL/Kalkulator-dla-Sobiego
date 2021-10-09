package me.minemis.calculator;

public class NumberStringData {
    public String numberString = "";
    public boolean isNegative = false;
    public boolean isPercent = false;
    public CalcEnum whichNumber;

    public NumberStringData(CalcEnum whichNumber) {
        this.whichNumber = whichNumber;
    }

    public void clear() {
        numberString = "";
        isNegative = false;
        isPercent = false;
    }
}
