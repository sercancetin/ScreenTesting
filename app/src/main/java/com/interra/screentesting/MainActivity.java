package com.interra.screentesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.interra.screentesting.activity.DrawingActivity;
import com.interra.screentesting.activity.MultiTouchActivity;
import com.interra.screentesting.activity.ScreenColorActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_color_testing;
    Button btn_touch_testing;
    Button btn_multi_touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        btn_color_testing = findViewById(R.id.btn_color_testing);
        btn_touch_testing = findViewById(R.id.btn_touch_testing);
        btn_multi_touch = findViewById(R.id.btn_multi_touch);

        btn_color_testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScreenColorActivity.class));
            }
        });
        btn_touch_testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawingActivity.class));
            }
        });

        btn_multi_touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultiTouchActivity.class));
            }
        });
    }
}