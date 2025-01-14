package com.example.yiearshampoo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final int DEFAULT_TIMER_VALUE = 30;
    private static final long TOTAL_TIME = DEFAULT_TIMER_VALUE * 1000;
    private static final long INTERVAL_TIME = 1000;

    public static Intent newIntent(Context context){
        return new Intent(context, GameActivity.class);
    }
    private View rendaView;
    private TextView timerText;

    private ImageView shampoo;
    private CountDownTimer timer;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        shampoo = findViewById(R.id.shampoo);
        timerText = findViewById(R.id.timer_text);
        rendaView = findViewById(R.id.renda);

        rendaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++count;

            }
        });
        initTimer();
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
    }

    private void initTimer(){
        timerText.setText(String.valueOf(DEFAULT_TIMER_VALUE));
        timer = new CountDownTimer(TOTAL_TIME, INTERVAL_TIME) {
            @Override
            public void onTick(long millisUnitlFinished) {
                long second = millisUnitlFinished / 1000;
                timerText.setText(String.valueOf(second));
            }

            @Override
            public void onFinish() {
                rendaView.setOnClickListener(null);
                Intent intent = ClearActivity
                        .newIntent(GameActivity.this, count);
                startActivity(intent);



            }
        };
    }
}