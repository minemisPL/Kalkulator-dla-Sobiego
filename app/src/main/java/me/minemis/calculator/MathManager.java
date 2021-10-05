package me.minemis.calculator;

public class MathManager {
    private double firstNumber = Double.NaN;
    private double secondNumber = Double.NaN;
    private String operator;

    public void setNumber(String input, CalcEnum calcEnum) {

        String[] split = input.split("\\.");

        long decimal = Long.parseLong(split[0]);
        long point = 0;

        if (split.length == 2) {
            point = Long.parseLong(split[1]);
        }

        if (decimal < 0) {
            point *= -1;
        }

        double doubleNumber = decimal + point / (Math.pow(10, String.valueOf(point).length()));

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
        double result = Double.NaN;

        if (operator.equals("+")) {
            result =  firstNumber + secondNumber;
        }

        if (operator.equals("-")) {
            result = firstNumber - secondNumber;
        }

        if (operator.equals("*")) {
            result = firstNumber * secondNumber;
        }

        if (operator.equals("/")) {
            result = secondNumber == 0 ? 0 : firstNumber / secondNumber;
        }

        firstNumber = result;
        secondNumber = Double.NaN;

        return result;
    }

    public void equalsReset() {
        firstNumber = secondNumber;
        secondNumber = Double.NaN;
    }
}
