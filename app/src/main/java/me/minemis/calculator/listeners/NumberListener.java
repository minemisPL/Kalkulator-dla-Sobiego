package me.minemis.calculator.listeners;

import android.view.View;
import android.widget.Button;
import me.minemis.calculator.MainActivity;

public class NumberListener extends CalcListener {
    public NumberListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        String textFromButton = ((Button) view).getText().toString();

        inputStringManager.appendNextNumber(textFromButton);
        editText.setText(inputStringManager.getResultString());
    }
}
