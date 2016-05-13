package com.louisgeek.louislayoutdemos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by louisgeek on 2016/5/13.
 */
public class CircleLikeProgressbar extends View{

    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    private float mStrokeWidth = 40;
    private float mHalfStrokeWidth = mStrokeWidth / 2;
    private float mRadius = 100;
    private RectF mRect;
    private int mProgress = 0;
    //目标值，想改多少就改多少
    private int mTargetProgress = 80;
    private int mMax = 100;
    private int mWidth;
    private int mHeight;


    public CircleLikeProgressbar(Context context) {
        super(context);
        init();
    }

    public CircleLikeProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CircleLikeProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.WHITE);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(Color.DKGRAY);
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(30);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }
    //重写测量大小的onMeasure方法和绘制View的核心方法onDraw()
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);

    }
    private int getRealSize(int measureSpec) {
        int result = 1;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            //自己计算
            result = (int) (mRadius * 2 + mStrokeWidth);
        } else {
            result = size;
        }

        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        initRectF();
        float angle = mProgress / (float) mMax * 360;
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackPaint);
        canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
        canvas.drawText(mProgress + "%", mWidth / 2 + mHalfStrokeWidth, mHeight / 2 + mHalfStrokeWidth, mTextPaint);
        if (mProgress < mTargetProgress) {
            mProgress += 1;
            invalidate();
        }

    }

    private void initRectF() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (mWidth - viewSize) / 2;
            int top = (mHeight - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            mRect.set(left, top, right, bottom);
        }
    }

}
