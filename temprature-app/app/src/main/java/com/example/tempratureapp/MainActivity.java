package com.example.tempratureapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText celsiusInput, fahrenheitInput;
    Button convertButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsiusInput = findViewById(R.id.celci);
        fahrenheitInput = findViewById(R.id.fera);
        convertButton = findViewById(R.id.converter);
        resultText = findViewById(R.id.resultText1);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values=]
                String celsiusText = celsiusInput.getText().toString();
                String fahrenheitText = fahrenheitInput.getText().toString();

                if (!celsiusText.isEmpty()) {
                    // Convert Celsius to Fahrenheit
                    double celsius = Double.parseDouble(celsiusText);
                    double fahrenheit = (celsius * 9/5) + 32;
                    resultText.setText("Fahrenheit: " + String.format("%.2f", fahrenheit));
                } else if (!fahrenheitText.isEmpty()) {
                    // Convert Fahrenheit to Celsius
                    double fahrenheit = Double.parseDouble(fahrenheitText);
                    double celsius = (fahrenheit - 32) * 5/9;
                    resultText.setText("Celsius: " + String.format("%.2f", celsius));
                } else {
                    // Handle empty input
                    resultText.setText("Please enter a temperature.");
                }
            }
        });
    }
}
