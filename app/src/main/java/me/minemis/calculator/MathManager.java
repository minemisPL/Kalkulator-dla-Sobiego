package me.minemis.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class MathManager {
    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private String operator;

    public void setNumber(String input, CalcEnum calcEnum) {

        boolean isNegative = input.contains("-");

        String[] split = input.split("\\.");
        split[0] = split[0].replace("-", "");

        BigDecimal decimal = new BigDecimal(split[0]);
        BigDecimal point = new BigDecimal(0);

        if (split.length == 2) {
            point = new BigDecimal(split[1]);
        }

        System.out.println("------------------------------");
        System.out.println("Decimal: " + decimal);
        System.out.println("Point before changing sign: " + point);
        double pow = Math.pow(10, String.valueOf(point).length());

        System.out.println("Point after changing sign: " + point);

        BigDecimal divide = point.divide(BigDecimal.valueOf(pow), MathContext.DECIMAL64);
        System.out.println("Actual point: " + divide);
        BigDecimal doubleNumber = decimal.add(divide);

        if (isNegative) {
            doubleNumber = doubleNumber.multiply(BigDecimal.valueOf(-1));
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
        resultString = String.valueOf(firstNumber.divide(secondNumber, BigDecimal.ROUND_DOWN, 6));
        System.out.println("Divide: " + resultString);


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
                            : firstNumber.divide(secondNumber, BigDecimal.ROUND_DOWN, 6).doubleValue()));
        }

        firstNumber = result;

        return result.doubleValue();
    }
}
