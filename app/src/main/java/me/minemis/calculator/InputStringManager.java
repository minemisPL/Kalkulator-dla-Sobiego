package me.minemis.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InputStringManager {
    private final Map<CalcEnum, String> map = new HashMap<>();
    private CalcEnum selectedString = CalcEnum.FIRST;
    private final MathManager mathManager;

    public InputStringManager(MathManager mathManager) {
        this.mathManager = mathManager;
        map.put(CalcEnum.FIRST, "");
        map.put(CalcEnum.OPERATOR, "");
        map.put(CalcEnum.SECOND, "");
    }

    public void setNextNumber(String text) {
        String workingText = map.get(selectedString);

        if (workingText == null) {
            throw new NullPointerException();
        }

        if ("=".equals(map.get(CalcEnum.OPERATOR))) {
            return;
        }


        if (checkForDoubleDots(workingText, text)) {
            return;
        }

        workingText += text;
        map.put(selectedString, workingText);
    }

    public void setOperator(String operator) {
        map.put(CalcEnum.OPERATOR, operator);
        selectedString = CalcEnum.SECOND;
    }

    public boolean hasOperator() {
        return !Objects.equals(map.get(CalcEnum.OPERATOR), "");
    }

    public boolean hasSecondNumber() {
        return !Objects.equals(map.get(CalcEnum.SECOND), "");
    }

    public String resolveEquation() {
        mathManager.setNumber(map.get(CalcEnum.FIRST), CalcEnum.FIRST);
        mathManager.setNumber(map.get(CalcEnum.SECOND), CalcEnum.SECOND);
        mathManager.setOperator(map.get(CalcEnum.OPERATOR));

        String equation = String.valueOf(mathManager.resolveEquation());

        map.put(CalcEnum.FIRST, equation);
        map.put(CalcEnum.SECOND, "");

        return equation;
    }

    private boolean checkForDoubleDots(String numberString, String text) {
        return (numberString.contains(".") && text.equals("."))
                || numberString.isEmpty() && text.equals(".");
    }

    public String getResultString() {
        return map.get(CalcEnum.FIRST) + " " + getOperator() + " " + map.get(CalcEnum.SECOND);
    }

    public String getOperator() {
        String operator = map.get(CalcEnum.OPERATOR);
        if ("=".equals(operator)) {
            return "";
        }
        return operator;
    }


}
