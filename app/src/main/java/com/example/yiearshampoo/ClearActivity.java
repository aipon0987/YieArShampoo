package com.example.yiearshampoo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClearActivity extends AppCompatActivity {
    private static final String KEY_TAP_COUNT = "key_tap_count";

    private Button reStart;
    private Button backToTop;
    private TextView rendaTextView;
    private ImageView clearBoy;

    public static Intent newIntent(Context context, int count){
        Intent intent = new Intent(context, ClearActivity.class);
        intent.putExtra(KEY_TAP_COUNT, count);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int count = getIntent().getIntExtra(KEY_TAP_COUNT, 0);

        rendaTextView = findViewById(R.id.result_text);
        clearBoy = findViewById(R.id.clear_boy);
        rendaTextView.setText(String.valueOf(count));

        reStart = findViewById(R.id.reStart_button);
        reStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = GameActivity.newIntent(ClearActivity.this);
                startActivity(intent);

            }
        });
        backToTop = findViewById(R.id.backToTop_button);
        backToTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.newIntent(ClearActivity.this);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        if (count < 10) {
            clearBoy.setImageResource(R.drawable.lose_boy);
        } else if (count <= 30) {
            clearBoy.setImageResource(R.drawable.clear_boy);
        }  else if (count <= 50) {
            clearBoy.setImageResource(R.drawable.clear_shampoo);
        }
    }
}
