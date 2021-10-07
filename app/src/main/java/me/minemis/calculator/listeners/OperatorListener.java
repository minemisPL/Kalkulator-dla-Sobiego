package me.minemis.calculator.listeners;

import android.view.View;
import android.widget.Button;
import me.minemis.calculator.MainActivity;

public class OperatorListener extends CalcListener{
    public OperatorListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        String operator = ((Button) view).getText().toString();

        String resultText;

        if (!inputStringManager.hasFirstNumber()) {
            return;
        }

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
}
