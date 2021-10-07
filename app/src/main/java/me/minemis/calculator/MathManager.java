package me.minemis.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class MathManager {
    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private String operator;

    public void setNumber(String input, CalcEnum calcEnum) {

        String[] split = input.split("\\.");

        BigDecimal decimal = new BigDecimal(split[0]);
        BigDecimal point = new BigDecimal(0);

        if (split.length == 2) {
            point = new BigDecimal(split[1]);
        }

        if (decimal.doubleValue() < 0) {
            point = point.multiply(BigDecimal.valueOf(-1), MathContext.DECIMAL64);
        }
        double pow = Math.pow(10, String.valueOf(point).length());
        BigDecimal doubleNumber = decimal.add(point.divide(BigDecimal.valueOf(pow), MathContext.DECIMAL64));

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
                            : firstNumber.divide(secondNumber)));
        }

        firstNumber = result;

        return result.doubleValue();
    }
}
