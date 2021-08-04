package com.interra.screentesting.activity;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.interra.screentesting.R;
import com.interra.screentesting.customview.DrawView;
import com.interra.screentesting.baseactivity.BaseActivity;

public class DrawingActivity extends BaseActivity {
    DrawView drawView;
    DisplayMetrics metrics;
    Button btn_drawing_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        drawView = findViewById(R.id.draw_view);
        btn_drawing_back = findViewById(R.id.btn_drawing_back);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if(showNavigationBar(getResources())){
            drawView.init(metrics,getNavigationBarHeight());
        }else{
            drawView.init(metrics);
        }
        btn_drawing_back.setOnClickListener(v -> {
            finish();
        });
    }

    //gezinme çubuğu varsa
    public boolean showNavigationBar(Resources resources)
    {
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

    private int getNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            int usableHeight = metrics.heightPixels;
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }
}