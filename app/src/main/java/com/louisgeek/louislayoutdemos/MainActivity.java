package com.louisgeek.louislayoutdemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button idbtn = (Button) findViewById(R.id.id_btn);
        idbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });
        FrameLayout idfl = (FrameLayout) findViewById(R.id.id_fl);

        final MyView myView=new MyView(this);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: getBitmapW_H()" + myView.getBitmapW_H());
                Log.d(TAG, "onTouch: getWidth"+myView.getWidth());
                myView.updateView(event.getX()-myView.getBitmapW_H(),event.getY()-myView.getBitmapW_H());
                return false;
            }
        });
        idfl.addView(myView);
    }
}
