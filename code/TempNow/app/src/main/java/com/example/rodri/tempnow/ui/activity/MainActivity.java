package com.example.rodri.tempnow.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodri.tempnow.R;
import com.example.rodri.tempnow.ui.adapter.SpinnerAdapter;
import com.example.rodri.tempnow.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private SpinnerAdapter adapter;
    private EditText temperature;
    private Spinner scales;
    private TextView temp1;
    private TextView temp2;
    private Button convert;
    private List<String> scalesArray;
    private int selectedScale = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        scalesArray = Arrays.asList("", "Celsius", "Fahrenheit", "Kelvin");
        adapter = new SpinnerAdapter(this, 0, scalesArray);
        scales.setAdapter(adapter);

        scales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedScale = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scalesArray.get(selectedScale).isEmpty()) {
                    Toast.makeText(MainActivity.this, "You didn't choose a scale!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, scalesArray.get(selectedScale) + " was selected!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void initialize() {
        temperature = (EditText) findViewById(R.id.etTemperature);
        scales = (Spinner) findViewById(R.id.scaleSpinner);
        temp1 = (TextView) findViewById(R.id.txtTemp1);
        temp2 = (TextView) findViewById(R.id.txtTemp2);
        convert = (Button) findViewById(R.id.btConvert);
    }
}
