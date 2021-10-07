package me.minemis.calculator.listeners;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.minemis.calculator.InputStringManager;
import me.minemis.calculator.MainActivity;


public class NumberListener implements View.OnClickListener {

    private final EditText resultText;
    private final InputStringManager inputStringManager;

    public NumberListener(MainActivity mainActivity) {
        this.resultText = mainActivity.getResultText();
        this.inputStringManager = mainActivity.getInputStringManager();
    }

    @Override
    public void onClick(View view) {
        String textFromButton = ((Button) view).getText().toString();
        inputStringManager.setNextNumber(textFromButton);

        resultText.setText(inputStringManager.getResultString());
    }
}
