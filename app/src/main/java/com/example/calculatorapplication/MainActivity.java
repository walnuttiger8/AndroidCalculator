package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private InputGateway inputGateway;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputGateway = new InputGateway();
        calculator = new Calculator();
    }

    private TextView getMainInput() {
        return findViewById(R.id.mainInput);
    }

    public void inputButtonClick(View view) {
        Button b = (Button) view;
        String text = b.getText().toString();

        if (text.equals(getString(R.string.clear))) {
            inputGateway.clear();
        }
        else if (text.equals(getString(R.string.equal))) {
            try {
                calculate();
            } catch (Exception e) {
                inputGateway.clear();
            }
        }
        else {
            inputGateway.addSymbol(text);
        }
        updateInput();
    }

    private void calculate() {
        String expression = inputGateway.getData();
        inputGateway.clear();
        int result = calculator.calculate(expression).intValue();
        inputGateway.addSymbol(String.valueOf(result));
    }

    private void updateInput() {
        TextView t = getMainInput();
        t.setText(inputGateway.getData());
    }
}