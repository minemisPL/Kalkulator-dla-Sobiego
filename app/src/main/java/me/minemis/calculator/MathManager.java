package me.minemis.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class MathManager {
    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private String operator;

    public void setNumber(NumberStringData numberStringData) {

        String workingText = numberStringData.numberString;

        String[] split = workingText.split("\\.");

        BigDecimal decimal = new BigDecimal(split[0]);
        BigDecimal point = new BigDecimal(0);
        double pow = 1;

        if (split.length == 2) {
            point = new BigDecimal(split[1]);
            pow = Math.pow(10, split[1].length());
        }

        BigDecimal divide = point.divide(BigDecimal.valueOf(pow), MathContext.DECIMAL64);
        BigDecimal doubleNumber = decimal.add(divide);

        if (numberStringData.isNegative) {
            doubleNumber = doubleNumber.multiply(BigDecimal.valueOf(-1));
        }

        if (numberStringData.isPercent) {
            doubleNumber = doubleNumber.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_DOWN, 6);
        }

        if (numberStringData.whichNumber == CalcEnum.FIRST) {
            this.firstNumber = doubleNumber;
            return;
        }

        this.secondNumber = doubleNumber;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double resolveEquation() {
        BigDecimal result = new BigDecimal(0);

        if (this.operator.equals("+")) {
            result = new BigDecimal(String.valueOf(firstNumber.add(secondNumber)));
        }

        if (this.operator.equals("-")) {
            result = new BigDecimal(String.valueOf(firstNumber.subtract(secondNumber)));
        }

        if (this.operator.equals("*")) {
            result = new BigDecimal(String.valueOf(firstNumber.multiply(secondNumber)));
        }

        if (this.operator.equals("/")) {
            result = new BigDecimal(
                    String.valueOf(secondNumber.doubleValue() == 0.0
                            ? 0
                            : firstNumber.divide(secondNumber, BigDecimal.ROUND_HALF_DOWN, 6).doubleValue()));
        }

        this.firstNumber = result;

        return result.doubleValue();
    }
}