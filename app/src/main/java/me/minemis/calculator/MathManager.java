package me.minemis.calculator;

public class MathManager {
    private double firstNumber = Double.MIN_VALUE;
    private double secondNumber = Double.MIN_VALUE;
    private String operator;

    public void setNumber(String input, CalcEnum calcEnum) {

        String[] split = input.split("\\.");

        int decimal = Integer.parseInt(split[0]);
        int point = 0;

        if (input.contains(".")) {
            point = Integer.parseInt(split[1]);
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
        double result = Double.MIN_VALUE;

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
            if (secondNumber == 0) {
                result = 0;
            } else {
                result = firstNumber / secondNumber;
            }
        }

        firstNumber = result;
        secondNumber = Double.MIN_VALUE;

        return result;
    }

    public void equalsReset() {
        firstNumber = secondNumber;
        secondNumber = Double.MIN_VALUE;
    }

    public boolean checkIfSecondExist() {
        return secondNumber != Double.MIN_VALUE;
    }

    public boolean checkIfFirstExist() {
        return firstNumber != Double.MIN_VALUE;
    }
}
