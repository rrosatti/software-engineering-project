package com.example.rodri.tempnow.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodri.tempnow.R;
import com.example.rodri.tempnow.temperature.Temperature;
import com.example.rodri.tempnow.ui.adapter.SpinnerAdapter;
import com.example.rodri.tempnow.util.Util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SpinnerAdapter adapter;
    private EditText etTemperature;
    private Spinner scalesSpinner;
    private TextView txtTemp1;
    private TextView txtTemp2;
    private Button btConvert;
    private List<String> scalesArray;
    private int selectedScale = 0;
    private Temperature temperature;
    private Typeface custom_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomToolBar();
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
                String tempString = etTemperature.getText().toString();
                if (scalesArray.get(selectedScale).isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.no_scale_selected, Toast.LENGTH_LONG).show();
                } else if (tempString.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.no_temperature_value, Toast.LENGTH_LONG).show();
                } else {

                    double temp = 0.00;

                    try {
                        temp = Double.parseDouble(tempString);
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Invalid value!", Toast.LENGTH_SHORT).show();
                        temp = 0.00;
                        etTemperature.setText("0.00");
                        e.printStackTrace();
                    }

                    NumberFormat formatter = new DecimalFormat("#0.00");
                    temperature.setTemperature(temp);
                    switch (temperature.getScale()) {
                        case 'C':
                            txtTemp1.setText(formatter.format(temperature.celsiusToFahrenheit()) + " ºF");
                            txtTemp2.setText(formatter.format(temperature.celsiusToKelvin()) + " K");
                            break;
                        case 'F':
                            txtTemp1.setText(formatter.format(temperature.fahrenheitToCelsius()) + " ºC");
                            txtTemp2.setText(formatter.format(temperature.fahrenheitToKelvin()) + " K");
                            break;
                        case 'K':
                            txtTemp1.setText(formatter.format(temperature.kelvinToCelsius()) + " ºC");
                            txtTemp2.setText(formatter.format(temperature.kelvinToFahrenheit()) + " ºF");
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
        custom_font = Typeface.createFromAsset(getAssets(), "fonts/UbuntuTitling-Bold.ttf");

        txtTemp1.setTypeface(custom_font);
        txtTemp2.setTypeface(custom_font);
        btConvert.setTypeface(custom_font);
        etTemperature.setTypeface(custom_font);

    }

    public void setCustomToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.txtToolBarTitle);
        custom_font = Typeface.createFromAsset(getAssets(), "fonts/UbuntuTitling-Bold.ttf");
        title.setTypeface(custom_font);
        title.setText(R.string.app_name);
    }
}
