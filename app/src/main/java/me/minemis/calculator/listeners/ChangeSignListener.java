package me.minemis.calculator.listeners;

import android.view.View;
import me.minemis.calculator.MainActivity;

public class ChangeSignListener extends CalcListener {
    public ChangeSignListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        inputStringManager.changeSign();

        editText.setText(inputStringManager.getResultString());
    }
}
