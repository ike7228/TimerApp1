package com.example.ikeproject_timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME = 10000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button getButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunnning;

    private long mTimeLeftInMilles = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        getButtonReset = findViewById(R.id.buttonreset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mTimerRunnning){
                    pauseTimer();
                }else{
                    startTimer();
                }

            }

        });

        getButtonReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                resetTimer();
            }

        });

        updateCountDownText();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMilles,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilles = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunnning = false;
                mButtonStartPause.setText("スタート");
                getButtonReset.setVisibility(View.INVISIBLE);

            }
        }.start();
        mTimerRunnning = true;
        mButtonStartPause.setText("一時停止");
        getButtonReset.setVisibility(View.INVISIBLE);


    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunnning = false;
        mButtonStartPause.setText("スタート");
        getButtonReset.setVisibility(View.VISIBLE);


    }
    private void resetTimer(){
        mTimeLeftInMilles = START_TIME;
        updateCountDownText();
        mButtonStartPause.setVisibility(View.VISIBLE);
        getButtonReset.setVisibility(View.INVISIBLE);

    }

    private void updateCountDownText(){
        int minutes = (int)(mTimeLeftInMilles/1000)/60;
        int seconds = (int)(mTimeLeftInMilles/1000)%60;
        String timerLeftFormated = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timerLeftFormated);

    }


}
