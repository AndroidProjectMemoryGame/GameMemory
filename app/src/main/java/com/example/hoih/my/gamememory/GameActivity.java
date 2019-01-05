package com.example.hoih.my.gamememory;

import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

public class GameActivity extends AppCompatActivity {

    TextView tv_second_left, tv_second_right, tv_time;
    ImageButton btnBack;
    CountDownTimer cTimer = null;
    int time, timeCountUp = 0;
    int timeCountDownInterval = 1000;
    boolean isCountDownFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv_second_left = (TextView)findViewById(R.id.tv_second_left);
        tv_second_right = (TextView)findViewById(R.id.tv_second_right);
        tv_time = (TextView)findViewById(R.id.tv_time);
        btnBack = (ImageButton) findViewById(R.id.btn_icon_back);


        if (getIntent() != null) {
            if (getIntent().getIntExtra("levelSelected", 0) == 5) {
                time = 5000;
            }
            if (getIntent().getIntExtra("levelSelected", 0) == 7) {
                time = 7000;
            }
            if (getIntent().getIntExtra("levelSelected", 0) == 10){
                time = 10000;
            }
        }

        // Start timer
        startTimer(time);

        // handle back icon
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void startTimer(int time) {
        new CountDownTimer(time, timeCountDownInterval) {

            public void onTick(long millisUntilFinished) {
                tv_second_left.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tv_second_left.setText("0");
                
                // chưa xong
                isCountDownFinish = true;
                if (isCountDownFinish == true) {
                    // continue countUp Timer
                    getCountUpTimer(timeCountUp);
                }
            }
        }.start();
    }

    public void getCountUpTimer(int timeCountUp) {
        new CountDownTimer(timeCountUp, timeCountDownInterval) {

            public void onTick(long millisUntilFinished) {
                tv_second_left.setText("" + millisUntilFinished + 1000);
            }

            public void onFinish() {
                tv_second_left.setText("0");
            }
        }.start();
    }

    public void cancelTimer() {
        if(cTimer != null)
            cTimer.cancel();
    }
}