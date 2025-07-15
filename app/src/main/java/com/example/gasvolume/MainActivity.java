package com.example.gasvolume;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText volumeInput, pressureInput, temperatureInput;
    private TextView resultText;
    private Button calculateButton;

    private static final double STANDARD_PRESSURE = 1013.25; // hPa
    private static final double REFERENCE_TEMPERATURE = 298.15; // K
    private static final double STANDARD_TEMPERATURE = 275.15; // K

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumeInput = findViewById(R.id.volumeInput);
        pressureInput = findViewById(R.id.pressureInput);
        temperatureInput = findViewById(R.id.temperatureInput);
        resultText = findViewById(R.id.resultText);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String volumeText = volumeInput.getText().toString();
                String pressureText = pressureInput.getText().toString();
                String temperatureText = temperatureInput.getText().toString();

                if (volumeText.isEmpty() || pressureText.isEmpty() || temperatureText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请填写所有字段", Toast.LENGTH_SHORT).show();
                    return;
                }

                double V = Double.parseDouble(volumeText);
                double P = Double.parseDouble(pressureText);
                double T = Double.parseDouble(temperatureText);

                double V0 = V * (P / STANDARD_PRESSURE) * (STANDARD_TEMPERATURE / T);
                double Vr = V * (P * REFERENCE_TEMPERATURE) / (STANDARD_PRESSURE * T);

                V0 = Math.round(V0 * 100.0) / 100.0;
                Vr = Math.round(Vr * 100.0) / 100.0;

                resultText.setText("标况体积: " + V0 + " L\n参比体积: " + Vr + " L");
            }
        });
    }
}
