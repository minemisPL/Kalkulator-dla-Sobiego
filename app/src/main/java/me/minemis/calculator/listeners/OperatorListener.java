package me.minemis.calculator.listeners;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.minemis.calculator.MainActivity;
import me.minemis.calculator.MathManager;


public class OperatorListener implements View.OnClickListener {

    private final EditText resultText;
    private final MathManager mathManager;

    public OperatorListener(MainActivity mainActivity) {
        this.resultText = mainActivity.getResultText();
        this.mathManager = mainActivity.getMathManager();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String textFromCalculator = resultText.getText().toString();
        String operator = ((Button) view).getText().toString();

        if (textFromCalculator.isEmpty()) {
            return;
        }

        if (!mathManager.checkIfFirstExist()) {
            textFromCalculator = this.changeSignIfNecessary(textFromCalculator, operator);

            mathManager.setNextNumber(textFromCalculator);
            mathManager.setOperator(operator);
            resultText.setText(textFromCalculator + " " + operator + " ");
            System.out.println("Return after !mathManager.checkIfFirstExist()");
            return;
        }

        String secondNumber = textFromCalculator.split(" ")[2];
        secondNumber = this.changeSignIfNecessary(secondNumber, operator);

        mathManager.setNextNumber(secondNumber);
        double resolveEquation = mathManager.resolveEquation();

        System.out.println("Resolved equasion: " + resolveEquation);

        String resultEquation = String.valueOf(resolveEquation).replace(".0", "");
        System.out.println(resultEquation);

        if (!operator.equals("=")) {
           resultEquation += " " + operator + " ";
            System.out.println("resultEquation += \" \" + operator + \" \";");
        } else {
            mathManager.equalsReset();
            System.out.println("mathManager.equalsReset();");
        }

        mathManager.setOperator(operator);
        System.out.println("Operator: " + operator);

        resultText.setText(resultEquation);

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
