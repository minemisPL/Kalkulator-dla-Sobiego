package me.minemis.calculator.listeners;

import android.view.View;
import android.widget.EditText;

import me.minemis.calculator.InputStringManager;
import me.minemis.calculator.MainActivity;

public abstract class CalcListener implements View.OnClickListener {
    protected final EditText editText;
    protected final InputStringManager inputStringManager;

    public CalcListener(MainActivity mainActivity) {
        this.editText = mainActivity.getResultText();
        this.inputStringManager = mainActivity.getInputStringManager();
    }
}
