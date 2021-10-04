package me.minemis.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedHashMap;
import java.util.Map;

import me.minemis.calculator.listeners.NumberListener;
import me.minemis.calculator.listeners.OperatorListener;

public class MainActivity extends AppCompatActivity {

    private final Map<String, Button> buttons = new LinkedHashMap<>();
    private EditText resultText;
    private InputStringManager inputStringManager;
    private MathManager mathManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignValues();
        assignListeners();

    }

    private void assignListeners() {
        Button button;

        for (int i = 0; i <= 9; i++) {
            button = buttons.get(String.valueOf(i));

            if (button != null) {
            button.setOnClickListener(new NumberListener(this));
            }
        }

        button = buttons.get(".");
        if (button != null)
            button.setOnClickListener(new NumberListener(this));

        buttons.get("+").setOnClickListener(new OperatorListener(this));
        buttons.get("-").setOnClickListener(new OperatorListener(this));
        buttons.get("*").setOnClickListener(new OperatorListener(this));
        buttons.get("/").setOnClickListener(new OperatorListener(this));
        buttons.get("=").setOnClickListener(new OperatorListener(this));
        buttons.get("+/-").setOnClickListener(new OperatorListener(this));
    }

    private void assignValues() {
        mathManager = new MathManager();
        inputStringManager = new InputStringManager(mathManager);

        resultText =          findViewById(R.id.editTextTextPersonName);

        buttons.put("7",   (Button) findViewById(R.id.button10));
        buttons.put("8",   (Button) findViewById(R.id.button11));
        buttons.put("9",   (Button) findViewById(R.id.button12));
        buttons.put("/",   (Button) findViewById(R.id.button13));
        buttons.put("4",   (Button) findViewById(R.id.button14));
        buttons.put("5",   (Button) findViewById(R.id.button15));
        buttons.put("6",   (Button) findViewById(R.id.button16));
        buttons.put("*",   (Button) findViewById(R.id.button17));
        buttons.put("1",   (Button) findViewById(R.id.button18));
        buttons.put("2",   (Button) findViewById(R.id.button19));
        buttons.put("3",   (Button) findViewById(R.id.button20));
        buttons.put("-",   (Button) findViewById(R.id.button21));
        buttons.put("0",   (Button) findViewById(R.id.button22));
        buttons.put(".",   (Button) findViewById(R.id.button23));
        buttons.put("=",   (Button) findViewById(R.id.button24));
        buttons.put("+",   (Button) findViewById(R.id.button25));
        buttons.put("CE",  (Button) findViewById(R.id.button26));
        buttons.put("+/-", (Button) findViewById(R.id.button27));
        buttons.put("%",   (Button) findViewById(R.id.button28));
        buttons.put("DEL", (Button) findViewById(R.id.button29));
    }

    public EditText getResultText() {
        return resultText;
    }

    public MathManager getMathManager() {
        return mathManager;
    }

    public InputStringManager getInputStringManager() {
        return inputStringManager;
    }
}