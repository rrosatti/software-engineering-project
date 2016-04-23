package com.example.rodri.tempnow.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodri.tempnow.R;
import com.example.rodri.tempnow.temperature.Temperature;
import com.example.rodri.tempnow.ui.adapter.SpinnerAdapter;
import com.example.rodri.tempnow.util.Util;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private SpinnerAdapter adapter;
    private EditText etTemperature;
    private Spinner scalesSpinner;
    private TextView txtTemp1;
    private TextView txtTemp2;
    private Button btConvert;
    private List<String> scalesArray;
    private int selectedScale = 0;
    private Temperature temperature;
    private double temp1;
    private double temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        scalesArray = Arrays.asList("", "Celsius", "Fahrenheit", "Kelvin");
        adapter = new SpinnerAdapter(this, 0, scalesArray);
        scalesSpinner.setAdapter(adapter);

        scalesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedScale = position;
                switch (position) {
                    case 1:
                        temperature.setScale('C');
                        break;
                    case 2:
                        temperature.setScale('F');
                        break;
                    case 3:
                        temperature.setScale('K');
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scalesArray.get(selectedScale).isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.no_scale_selected, Toast.LENGTH_LONG).show();
                } else if (etTemperature.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_temperature_value, Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(MainActivity.this, scalesArray.get(selectedScale) + " was selected!", Toast.LENGTH_LONG).show();
                    temperature.setTemperature(Long.parseLong(etTemperature.getText().toString()));
                    switch (temperature.getScale()) {
                        case 'C':
                            temp1 = temperature.celsiusToFahrenheit();
                            txtTemp1.setText(temperature.celsiusToFahrenheit() + " ºF");
                            temp2 = temperature.celsiusToKelvin();
                            txtTemp2.setText(temperature.celsiusToKelvin() + " K");
                            break;
                        case 'F':
                            temp1 = temperature.fahrenheitToCelsius();
                            txtTemp1.setText(temperature.fahrenheitToCelsius() + " ºC");
                            temp2 = temperature.fahrenheitToKelvin();
                            txtTemp2.setText(temperature.fahrenheitToKelvin() + " K");
                            break;
                        case 'K':
                            temp1 = temperature.kelvinToCelsius();
                            txtTemp1.setText(temperature.kelvinToCelsius() + " ºC");
                            temp2 = temperature.kelvinToFahrenheit();
                            txtTemp2.setText(temperature.kelvinToFahrenheit() + " ºF");
                            break;
                    }
                }

            }
        });
    }

    public void initialize() {
        etTemperature = (EditText) findViewById(R.id.etTemperature);
        scalesSpinner = (Spinner) findViewById(R.id.scaleSpinner);
        txtTemp1 = (TextView) findViewById(R.id.txtTemp1);
        txtTemp2 = (TextView) findViewById(R.id.txtTemp2);
        btConvert = (Button) findViewById(R.id.btConvert);
        temperature = new Temperature();
    }
}
