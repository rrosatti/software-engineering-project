package com.example.rodri.tempnow.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodri.tempnow.R;
import com.example.rodri.tempnow.util.Util;

/**
 * Created by rodri on 4/22/2016.
 */
public class SplashScreenActivity extends Activity {

    final boolean _active = true;
    final long _splashTime = 3500;
    private ImageView tempNowImage;
    private TextView txtTempNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        initialize();


        /** Get width and height from phone screen */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        tempNowImage.setImageResource(R.drawable.tempnow_thermometer);
        tempNowImage.getLayoutParams().width = (int) (0.70 * width);
        tempNowImage.getLayoutParams().height = (int) (0.45 * height);
        tempNowImage.requestLayout();

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/FingerPaint-Regular.ttf");
        txtTempNow.setTypeface(custom_font);

        final Thread splashThread = new Thread() {

            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) waited += 100;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                    Intent startApp = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(startApp);
                }
            }

        };
        splashThread.start();

    }

    public void initialize() {
        tempNowImage = (ImageView) findViewById(R.id.tempNowImageView);
        txtTempNow = (TextView) findViewById(R.id.txtTempNow);
    }
}
