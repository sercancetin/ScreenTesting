package com.interra.screentesting.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.interra.screentesting.R;
import com.interra.screentesting.baseactivity.BaseActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ScreenColorActivity extends BaseActivity {
    ImageView img_screen;
    View wrapper;
    int touch_count=0;

    Handler handler;

    Bitmap bmp_pattern;
    Canvas canvas;
    int vHeight;
    int vWidth;
    View viewLayout;
    private MyView mMyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewLayout = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_screen_color,null);
        viewLayout.setKeepScreenOn(true);
        setContentView(viewLayout);
        init();

        final Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.s);
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        wrapper.setBackgroundDrawable(bitmapDrawable);

    }
    private void init(){

        img_screen = findViewById(R.id.img_screen);
        wrapper = findViewById(R.id.wrapper);


        clickView();
        //colorAutomatic();

        mMyView = new MyView(this);
        mMyView.setKeepScreenOn(true);
        mMyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                touch_count++;
                if (touch_count == 6) {
                    setContentView(viewLayout);
                    touch_count= 0;
                    //activity close when you show all
                    onBackPressed();
                    return;
                }
                PatternsCreate(touch_count);
                mMyView.invalidate();
            }
        });
    }
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                display.getRealMetrics(metrics);
                this.vWidth = metrics.widthPixels;
                this.vHeight = metrics.heightPixels;
            } else {
                Method mGetRawH = Display.class.getMethod("getRawHeight", new Class[0]);
                try {
                    this.vWidth = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
                    this.vHeight = ((Integer) mGetRawH.invoke(display, new Object[0])).intValue();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (NoSuchMethodException e32) {
            e32.printStackTrace();
        }
        this.bmp_pattern = Bitmap.createBitmap(this.vWidth, this.vHeight, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bmp_pattern);
    }
    private void clickView(){
        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch_count++;
                mSwitch();
            }
        });
    }

    private void colorAutomatic(){
        handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                touch_count++;
                handler.postDelayed(this, 1500);
                mSwitch();

            }
        };

        handler.postDelayed(r, 1500);
    }

    private void mSwitch(){
        switch (touch_count%11){
            case 0:
                img_screen.setBackgroundColor(Color.argb(255, 255, 255, 255));
                return;
            case 1:
                img_screen.setBackgroundColor(Color.argb(255, 128, 128, 128));
                return;
            case 2:
                img_screen.setBackgroundColor(Color.argb(255, 0, 0, 0));
                return;
            case 3:
                img_screen.setBackgroundColor(SupportMenu.CATEGORY_MASK);
                return;
            case 4:
                img_screen.setBackgroundColor(Color.argb(255, 255, 165, 0));
                return;
            case 5:
                img_screen.setBackgroundColor(Color.argb(255, 255, 255, 0));
                return;
            case 6:
                img_screen.setBackgroundColor(Color.argb(255, 0, 255, 0));
                return;
            case 7:
                img_screen.setBackgroundColor(Color.argb(255, 0, 128, 255));
                return;
            case 8:
                img_screen.setBackgroundColor(Color.argb(255, 0, 0, 255));
                return;
            case 9:
                img_screen.setBackgroundColor(Color.argb(255, 139, 0, 255));
                return;
            case 10:
                img_screen.setBackgroundColor(Color.argb(255, 255, 255, 255));
                touch_count = 0;
                PatternsCreate(touch_count);
                setContentView((View) mMyView);
                return;
            default:
                return;
        }
    }

    public class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        /* Access modifiers changed, original: protected */
        public void onDraw(Canvas canvas) {
            canvas.drawBitmap(bmp_pattern, 0.0f, 0.0f, null);
        }
    }
    public void PatternsCreate(int index) {
        Paint paint = new Paint();
        int step;
        int i;
        int j;
        int step_val;
        int border;
        int x_range;
        int y_range;
        int each_width;
        int x_remain;
        int r_pos;
        int t_pos;
        int b_pos;
        int l_pos;
        int y_color_num = 1;
        switch (index) {
            case 0:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.canvas.drawPaint(paint);
                paint.setColor(-1);
                step = Math.max(this.vWidth, this.vHeight) / 174;
                for (i = this.vWidth / 2; i <= this.vWidth; i += step) {
                    this.canvas.drawLine((float) i, 0.0f, (float) i, (float) this.vHeight, paint);
                    this.canvas.drawLine((float) (this.vWidth - i), 0.0f, (float) (this.vWidth - i), (float) this.vHeight, paint);
                }
                for (j = this.vHeight / 2; j <= this.vHeight; j += step) {
                    this.canvas.drawLine(0.0f, (float) j, (float) this.vWidth, (float) j, paint);
                    this.canvas.drawLine(0.0f, (float) (this.vHeight - j), (float) this.vWidth, (float) (this.vHeight - j), paint);
                }
                return;
            case 1:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.canvas.drawPaint(paint);
                paint.setColor(-1);
                step = Math.max(this.vWidth, this.vHeight) / 174;
                for (i = 1; i <= this.vWidth; i += step) {
                    for (j = 1; j <= this.vHeight; j += step) {
                        this.canvas.drawPoint((float) i, (float) j, paint);
                    }
                }
                return;
            case 2:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.argb(255, 47, 47, 47));
                this.canvas.drawPaint(paint);
                paint.setColor(-1);
                step = Math.max(this.vWidth, this.vHeight) / 29;
                paint.setStrokeWidth(1.0f);
                i = this.vWidth / 2;
                while (i <= this.vWidth) {
                    this.canvas.drawLine((float) i, 0.0f, (float) i, (float) this.vHeight, paint);
                    this.canvas.drawLine((float) (this.vWidth - i), 0.0f, (float) (this.vWidth - i), (float) this.vHeight, paint);
                    i += step;
                }
                j = this.vHeight / 2;
                while (j <= this.vHeight) {
                    this.canvas.drawLine(0.0f, (float) j, (float) this.vWidth, (float) j, paint);
                    this.canvas.drawLine(0.0f, (float) (this.vHeight - j), (float) this.vWidth, (float) (this.vHeight - j), paint);
                    j += step;
                }
                paint.setStyle(Paint.Style.STROKE);
                int radius = step * 2;
                int x = this.vWidth - ((i - radius) - step);
                int y = this.vHeight - ((j - radius) - step);
                paint.setColor(-1);
                paint.setStrokeWidth(0.0f);
                this.canvas.drawCircle((float) x, (float) y, (float) radius, paint);
                paint.setColor(InputDeviceCompat.SOURCE_ANY);
                paint.setStrokeWidth(3.0f);
                this.canvas.drawLine((float) (x - (step / 2)), (float) y, (float) ((step / 2) + x), (float) y, paint);
                this.canvas.drawLine((float) x, (float) (y - (step / 2)), (float) x, (float) ((step / 2) + y), paint);
                x = (i - radius) - step;
                y = this.vHeight - ((j - radius) - step);
                paint.setColor(-1);
                paint.setStrokeWidth(0.0f);
                this.canvas.drawCircle((float) x, (float) y, (float) radius, paint);
                paint.setColor(InputDeviceCompat.SOURCE_ANY);
                paint.setStrokeWidth(3.0f);
                this.canvas.drawLine((float) (x - (step / 2)), (float) y, (float) ((step / 2) + x), (float) y, paint);
                this.canvas.drawLine((float) x, (float) (y - (step / 2)), (float) x, (float) ((step / 2) + y), paint);
                x = this.vWidth - ((i - radius) - step);
                y = (j - radius) - step;
                paint.setColor(-1);
                paint.setStrokeWidth(0.0f);
                this.canvas.drawCircle((float) x, (float) y, (float) radius, paint);
                paint.setColor(InputDeviceCompat.SOURCE_ANY);
                paint.setStrokeWidth(3.0f);
                this.canvas.drawLine((float) (x - (step / 2)), (float) y, (float) ((step / 2) + x), (float) y, paint);
                this.canvas.drawLine((float) x, (float) (y - (step / 2)), (float) x, (float) ((step / 2) + y), paint);
                x = (i - radius) - step;
                y = (j - radius) - step;
                paint.setColor(-1);
                paint.setStrokeWidth(0.0f);
                this.canvas.drawCircle((float) x, (float) y, (float) radius, paint);
                paint.setColor(InputDeviceCompat.SOURCE_ANY);
                paint.setStrokeWidth(3.0f);
                this.canvas.drawLine((float) (x - (step / 2)), (float) y, (float) ((step / 2) + x), (float) y, paint);
                this.canvas.drawLine((float) x, (float) (y - (step / 2)), (float) x, (float) ((step / 2) + y), paint);
                x = this.vWidth / 2;
                y = this.vHeight / 2;
                radius = (i - step) - Math.min(x, y);
                paint.setColor(-1);
                paint.setStrokeWidth(0.0f);
                this.canvas.drawCircle((float) x, (float) y, (float) radius, paint);
                paint.setColor(InputDeviceCompat.SOURCE_ANY);
                paint.setStrokeWidth(3.0f);
                this.canvas.drawLine((float) (x - (step / 2)), (float) y, (float) ((step / 2) + x), (float) y, paint);
                this.canvas.drawLine((float) x, (float) (y - (step / 2)), (float) x, (float) ((step / 2) + y), paint);
                paint.setStrokeWidth(0.0f);
                return;
            case 3:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(-1);
                this.canvas.drawPaint(paint);
                paint.setColor(-1);
                step = 256 / 256;
                step_val = 255;
                int x_step = this.vWidth / InputDeviceCompat.SOURCE_KEYBOARD;
                int x_mod = this.vWidth % InputDeviceCompat.SOURCE_KEYBOARD;
                int x_pos_left = 0;
                for (i = 0; i < 257; i++) {
                    int x_pos_right;
                    if (i < x_mod) {
                        x_pos_right = (x_pos_left + x_step) + 1;
                    } else {
                        x_pos_right = x_pos_left + x_step;
                    }
                    if (x_pos_right > this.vWidth) {
                        x_pos_right = this.vWidth;
                    }
                    if (step_val < 0) {
                        step_val = 0;
                    }
                    paint.setColor(Color.rgb(step_val, step_val, step_val));
                    this.canvas.drawRect((float) x_pos_left, 0.0f, (float) x_pos_right, (float) (this.vHeight / 2), paint);
                    int step_val_inv = 255 - step_val;
                    paint.setColor(Color.rgb(step_val_inv, step_val_inv, step_val_inv));
                    this.canvas.drawRect((float) x_pos_left, (float) (this.vHeight / 2), (float) x_pos_right, (float) this.vHeight, paint);
                    x_pos_left = x_pos_right;
                    step_val--;
                }
                return;
            case 4:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.canvas.drawPaint(paint);
                paint.setColor(-1);
                step = 256 / 16;
                step_val = 255;
                border = Math.max(this.vWidth, this.vHeight) / 86;
                x_range = this.vWidth - (border * 2);
                y_range = this.vHeight - (border * 2);
                int y_step = y_range / 17;
                int y_mod = y_range % 17;
                int[] color_scale = new int[]{Color.argb(0, 255, 255, 255),
                        Color.argb(0, 255, 0, 0),
                        Color.argb(0, 255, 165, 0),
                        Color.argb(0, 255, 255, 0),
                        Color.argb(0, 0, 255, 0),
                        Color.argb(0, 0, 255, 255),
                        Color.argb(0, 0, 255, 255),
                        Color.argb(0, 0, 0, 255),
                        Color.argb(0, 255, 0, 255),
                        Color.argb(0, 255, 0, 255)};
                int color_num = color_scale.length;
                each_width = (x_range - ((color_num - 1) * border)) / color_num;
                x_remain = (this.vWidth - ((color_num + 1) * border)) - (each_width * color_num);
                int y_pos_top = border;
                r_pos = x_remain / 2;
                for (i = 0; i < 17; i++) {
                    int y_pos_bottom;
                    if (i < y_mod) {
                        y_pos_bottom = (y_pos_top + y_step) + 1;
                    } else {
                        y_pos_bottom = y_pos_top + y_step;
                    }
                    if (y_pos_bottom > border + y_range) {
                        y_pos_bottom = border + y_range;
                    }
                    if (step_val < 0) {
                        step_val = 0;
                    }
                    r_pos = x_remain / 2;
                    t_pos = y_pos_top;
                    b_pos = y_pos_bottom;
                    for (j = 0; j < 10; j++) {
                        l_pos = r_pos + border;
                        r_pos = l_pos + each_width;
                        paint.setColor(((step_val & 255) << 24) | color_scale[j]);
                        this.canvas.drawRect((float) l_pos, (float) t_pos, (float) r_pos, (float) b_pos, paint);
                    }
                    y_pos_top = y_pos_bottom;
                    step_val -= 16;
                }
                return;
            case 5:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.canvas.drawPaint(paint);
                paint.setColor(-1);
                border = Math.max(this.vWidth, this.vHeight) / 60;
                x_range = this.vWidth - (border * 2);
                y_range = this.vHeight - (border * 2);
                int[][] color_chips;
                color_chips = new int[6][];
                color_chips[0] = new int[]{Color.argb(255, 243, 243, 242), Color.argb(255, 56, 61, 150), Color.argb(255, 214, 255, 44), Color.argb(255, 115, 82, 68)};
                color_chips[1] = new int[]{Color.argb(255, 200, 200, 200), Color.argb(255, 70, 148, 73), Color.argb(255, 80, 91, 166), Color.argb(255, 194, 150, 255)};
                color_chips[2] = new int[]{Color.argb(255, 160, 160, 160), Color.argb(255, 175, 54, 60), Color.argb(255, 193, 90, 99), Color.argb(255, 98, 122, 157)};
                color_chips[3] = new int[]{Color.argb(255, 122, 122, 121), Color.argb(255, 231, 199, 31), Color.argb(255, 94, 60, 108), Color.argb(255, 87, 108, 67)};
                color_chips[4] = new int[]{Color.argb(255, 85, 85, 85), Color.argb(255, 187, 86, 149), Color.argb(255, 157, 188, 64), Color.argb(255, 133, 128, 177)};
                color_chips[5] = new int[]{Color.argb(255, 52, 52, 52), Color.argb(255, 8, 133, 161), Color.argb(255, 224, 163, 46), Color.argb(255, 103, 189, 170)};
                int x_color_num = color_chips[0].length;
                each_width = (x_range - ((x_color_num - 1) * border)) / x_color_num;
                x_remain = (this.vWidth - ((x_color_num + 1) * border)) - (each_width * x_color_num);
                int each_height = (y_range - ((y_color_num - 1) * border)) / y_color_num;
                r_pos = x_remain / 2;
                b_pos = ((this.vHeight - ((y_color_num + 1) * border)) - (each_height * y_color_num)) / 2;
                for (int[] ıArr : color_chips) {
                    t_pos = b_pos + border;
                    b_pos = t_pos + each_height;
                    r_pos = 0;
                    for (j = 0; j < x_color_num; j++) {
                        l_pos = r_pos + border;
                        r_pos = l_pos + each_width;
                        paint.setColor(ıArr[j]);
                        this.canvas.drawRect((float) l_pos, (float) t_pos, (float) r_pos, (float) b_pos, paint);
                    }
                }
                return;
            default:
                return;
        }
    }
}