package me.minemis.calculator.listeners;

import android.view.View;
import me.minemis.calculator.MainActivity;

public class ClearListener extends CalcListener {
    public ClearListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        inputStringManager.clear();
        editText.setText(inputStringManager.getResultString());
    }
}
