package me.minemis.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class MathManager {
    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private String operator;

    public void setNumber(String input, CalcEnum calcEnum) {

        boolean isNegative = input.contains("-");
        boolean isPercent = false;

        String workingText = input;

        if (input.contains("%")) {
            isPercent = true;
            workingText = workingText.replace("%", "");
        }

        String[] split = workingText.split("\\.");
        split[0] = split[0].replace("-", "");

        BigDecimal decimal = new BigDecimal(split[0]);
        BigDecimal point = new BigDecimal(0);
        double pow = 1;

        if (split.length == 2) {
            point = new BigDecimal(split[1]);
            pow = Math.pow(10, split[1].length());
        }

        System.out.println("------------------------------");
        System.out.println("Decimal: " + decimal);
        System.out.println("Point before changing sign: " + point);


        System.out.println("Point after changing sign: " + point);

        BigDecimal divide = point.divide(BigDecimal.valueOf(pow), MathContext.DECIMAL64);
        System.out.println("Actual point: " + divide);
        BigDecimal doubleNumber = decimal.add(divide);

        if (isNegative) {
            doubleNumber = doubleNumber.multiply(BigDecimal.valueOf(-1));
        }

        if (isPercent) {
            doubleNumber = doubleNumber.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_DOWN, 6);
        }

        if (calcEnum == CalcEnum.FIRST) {
            this.firstNumber = doubleNumber;
            return;
        }

        secondNumber = doubleNumber;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double resolveEquation() {
        BigDecimal result = new BigDecimal(0);

        String resultString = String.valueOf(firstNumber.add(secondNumber));
        System.out.println("------------------------------");
        System.out.println("First number: " + firstNumber);
        System.out.println("Second number: " + secondNumber);
        System.out.println("Add: " + resultString);
        resultString = String.valueOf(firstNumber.subtract(secondNumber));
        System.out.println("Subtract: " + resultString);
        resultString = String.valueOf(firstNumber.multiply(secondNumber));
        System.out.println("Multiply: " + resultString);


        if (operator.equals("+")) {
            result = new BigDecimal(String.valueOf(firstNumber.add(secondNumber)));
        }

        if (operator.equals("-")) {
            result = new BigDecimal(String.valueOf(firstNumber.subtract(secondNumber)));
        }

        if (operator.equals("*")) {
            result = new BigDecimal(String.valueOf(firstNumber.multiply(secondNumber)));
        }

        if (operator.equals("/")) {
            result = new BigDecimal(
                    String.valueOf(secondNumber.doubleValue() == 0.0
                            ? 0
                            : firstNumber.divide(secondNumber, BigDecimal.ROUND_HALF_DOWN, 6).doubleValue()));
        }

        firstNumber = result;

        return result.doubleValue();
    }
}