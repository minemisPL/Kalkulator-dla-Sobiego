package me.minemis.calculator.listeners;

import android.view.View;
import android.widget.EditText;

import me.minemis.calculator.InputStringManager;
import me.minemis.calculator.MainActivity;

public class ClearListener implements View.OnClickListener {
    private final EditText editText;
    private final InputStringManager inputStringManager;

    public ClearListener(MainActivity mainActivity) {
        this.editText = mainActivity.getResultText();
        this.inputStringManager = mainActivity.getInputStringManager();
    }

    @Override
    public void onClick(View view) {
        inputStringManager.clear();
        editText.setText(inputStringManager.getResultString());
    }
}
