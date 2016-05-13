package com.louisgeek.louislayoutdemos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by louisgeek on 2016/5/13.
 */
public class MyView extends View {
    public float bitmapShowX;
    public float bitmapShowY;

    public float getBitmapW_H() {
        return bitmapW_H;
    }

    public float bitmapW_H;
    public MyView(Context context) {
        this(context, null, 0);
        bitmapShowX=200;
        bitmapShowY=200;
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //创建,并且实例化Paint的对象
        Paint paint = new Paint();
        //根据图片生成位图对象
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        bitmapW_H=bitmap.getWidth();
        //绘制bitmap
        canvas.drawBitmap(bitmap, bitmapShowX, bitmapShowY,paint);
        //判断图片是否回收,木有回收的话强制收回图片
        if(bitmap.isRecycled())
        {
            bitmap.recycle();
        }
    }


    public void updateView(float bitmapShowX,float bitmapShowY) {
        this.bitmapShowX = bitmapShowX;
        this.bitmapShowY = bitmapShowY;
        this.invalidate();
    }

}
