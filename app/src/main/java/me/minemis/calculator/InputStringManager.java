package me.minemis.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputStringManager {
    private final Map<CalcEnum, NumberStringData> map = new HashMap<>();
    private String operator = "";
    private CalcEnum selectedString = CalcEnum.FIRST;
    private final MathManager mathManager;

    public InputStringManager(MathManager mathManager) {
        this.mathManager = mathManager;
        map.put(CalcEnum.FIRST, new NumberStringData(CalcEnum.FIRST));
        map.put(CalcEnum.SECOND, new NumberStringData(CalcEnum.SECOND));
    }

    public void appendNextNumber(String text) {
        NumberStringData numberStringData = map.get(selectedString);

        if (numberStringData == null) {
            return;
        }

        if (this.operator.equals("=")) {
            clear();
        }

        String workingText = numberStringData.numberString;

        if (checkForWrongSignUsage(workingText, text)) {
            return;
        }

        if (text.equals("%")) {
            numberStringData.isPercent = true;
            return;
        }

        workingText += text;
        numberStringData.numberString = workingText;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        if (!operator.equals("=")) {
            this.selectedString = CalcEnum.SECOND;
        }
    }

    public String resolveEquation() {
        NumberStringData firstNumber = map.get(CalcEnum.FIRST);
        NumberStringData secondNumber = map.get(CalcEnum.SECOND);

        if (firstNumber == null || secondNumber == null) {
            return "0";
        }

        mathManager.setNumber(firstNumber);
        mathManager.setNumber(secondNumber);
        mathManager.setOperator(this.operator);

        String equationResult = String.valueOf(mathManager.resolveEquation());

        NumberStringData resultNumber = new NumberStringData(CalcEnum.FIRST);

        if (equationResult.contains("-")) {
            resultNumber.isNegative = true;
        }

        if (Double.parseDouble(equationResult) % 1 == 0) {
            equationResult = equationResult.replace(".0", "");
        }

        resultNumber.numberString = equationResult.replace("-", "");

        map.put(CalcEnum.FIRST, resultNumber);
        secondNumber.clear();

        this.selectedString = CalcEnum.FIRST;

        return equationResult;
    }

    public void dropLast() {
        NumberStringData numberStringData = map.get(selectedString);
        if (numberStringData == null || numberStringData.numberString.isEmpty()) {
            return;
        }

        if (numberStringData.isPercent) {
            numberStringData.isPercent = false;
            return;
        }

        String numberString = numberStringData.numberString;

        numberString = numberString.substring(0, numberString.length() - 1);

        numberStringData.numberString = numberString;
    }

    public void changeSign() {
        NumberStringData numberStringData = map.get(selectedString);

        if (numberStringData  == null) {
            return;
        }

        numberStringData.isNegative = !numberStringData.isNegative;
    }

    public void clear() {
        map.values().forEach(NumberStringData::clear);
        this.operator = "";
        this.selectedString = CalcEnum.FIRST;
    }

    private boolean checkForWrongSignUsage(String numberString, String sign) {
        switch (sign) {
            case ".": return numberString.contains(".") || numberString.isEmpty();
            case "%": return numberString.contains("%") || numberString.isEmpty();
            default: return numberString.contains("%");
        }
    }

    public boolean hasOperator() {
        return !operator.equals("");
    }

    public boolean hasFirstNumber() {
        NumberStringData numberStringData = map.get(CalcEnum.FIRST);

        if (numberStringData == null) return false;
        return !numberStringData.numberString.isEmpty();
    }

    public boolean hasSecondNumber() {
        NumberStringData numberStringData = map.get(CalcEnum.SECOND);

        if (numberStringData == null) return false;
        return !numberStringData.numberString.isEmpty();
    }

    public String getResultString() {
        List<NumberStringData> stringData = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        stringData.add(map.get(CalcEnum.FIRST));
        stringData.add(map.get(CalcEnum.SECOND));

        for (NumberStringData numberstringData : stringData) {
            if (numberstringData == null) return "";

            String workingString = numberstringData.numberString;

            if (numberstringData.isNegative) {
                workingString = "-" + workingString;
            }

            if (numberstringData.isPercent) {
                workingString += "%";
            }

            strings.add(workingString);
        }
        return strings.get(0) + " " + getOperator() + " " + strings.get(1);
    }

    public String getOperator() {
        if (this.operator.equals("=")) return "";
        return this.operator;
    }
}