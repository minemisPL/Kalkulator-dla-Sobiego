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

//        String text = resultText.getText().toString();
//        Button button = (Button) view;
//
//        String rightNumber;
//        String[] spaceSplit =  text.split(" ");
//
//        switch (spaceSplit.length) {
//            case 1:
//            case 2: rightNumber = spaceSplit[0]; break;
//            case 3: rightNumber = spaceSplit[2]; break;
//            default: rightNumber = "0";
//        }
//
//        if (button.getText().equals(".") && rightNumber.contains(".")) {
//            return;
//        }
//
//        text += button.getText().toString();
//
//        resultText.setText(text);