package com.interra.screentesting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.interra.screentesting.R;
import com.interra.screentesting.baseactivity.BaseActivity;

public class MultiTouchActivity extends BaseActivity {
    Button btn_drawing_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);
        btn_drawing_back = findViewById(R.id.btn_drawing_back);
        btn_drawing_back.setOnClickListener(v -> {
            finish();
        });
    }
}