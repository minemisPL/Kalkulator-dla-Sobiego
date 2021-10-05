package me.minemis.calculator.listeners;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.minemis.calculator.InputStringManager;
import me.minemis.calculator.MainActivity;


public class OperatorListener implements View.OnClickListener {

    private final EditText editText;
    private final InputStringManager inputStringManager;

    public OperatorListener(MainActivity mainActivity) {
        this.editText = mainActivity.getResultText();
        this.inputStringManager = mainActivity.getInputStringManager();
    }

    @Override
    public void onClick(View view) {
        String operator = ((Button) view).getText().toString();

        if (!inputStringManager.hasSecondNumber()) {
            inputStringManager.setOperator(operator);
            editText.setText(inputStringManager.getResultString());
            return;
        }

        if (inputStringManager.hasOperator()) {
            String resultText = inputStringManager.resolveEquation();
            inputStringManager.setOperator(operator);
            resultText += " " + inputStringManager.getOperator();

            editText.setText(resultText);
        }
    }

}
