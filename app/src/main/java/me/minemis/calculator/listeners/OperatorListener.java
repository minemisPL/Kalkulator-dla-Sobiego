package me.minemis.calculator.listeners;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.minemis.calculator.InputStringManager;
import me.minemis.calculator.MainActivity;
import me.minemis.calculator.MathManager;


public class OperatorListener implements View.OnClickListener {

    private final EditText editText;
    private final MathManager mathManager;
    private final InputStringManager inputStringManager;

    public OperatorListener(MainActivity mainActivity) {
        this.editText = mainActivity.getResultText();
        this.mathManager = mainActivity.getMathManager();
        this.inputStringManager = mainActivity.getInputStringManager();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String operator = ((Button) view).getText().toString();

        String resultText;

        if (inputStringManager.hasOperator() && inputStringManager.hasSecondNumber()) {
            resultText = inputStringManager.resolveEquation();
            inputStringManager.setOperator(operator);
            resultText += " " + inputStringManager.getOperator();
        } else {
            inputStringManager.setOperator(operator);
            resultText = inputStringManager.getResultString();
        }

        editText.setText(resultText);
    }

    private String changeSignIfNecessary(String number, String operator) {
        String result = number;
        if (operator.equals("+/-")) {
            result = result.contains("-")
                    ? result.replace("-", "")
                    : "-" + result;
        }
        return result;
    }
}

//        String textFromCalculator = resultText.getText().toString();
//        String operator = ((Button) view).getText().toString();
//
//        if (textFromCalculator.isEmpty()) {
//            return;
//        }
//
//        if (!mathManager.checkIfFirstExist()) {
//            textFromCalculator = this.changeSignIfNecessary(textFromCalculator, operator);
//
//            mathManager.setNumber(textFromCalculator);
//            mathManager.setOperator(operator);
//            resultText.setText(textFromCalculator + " " + operator + " ");
//            return;
//        }
//
//        String secondNumber = textFromCalculator.split(" ")[2];
//        secondNumber = this.changeSignIfNecessary(secondNumber, operator);
//
//        mathManager.setNumber(secondNumber);
//        double resolveEquation = mathManager.resolveEquation();
//
//        String resultEquation = String.valueOf(resolveEquation).replace(".0", "");
//
//        if (!operator.equals("=")) {
//           resultEquation += " " + operator + " ";
//        } else {
//            mathManager.equalsReset();
//        }
//
//        mathManager.setOperator(operator);
//
//        resultText.setText(resultEquation);
