package com.example.pawe.organizer.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.example.pawe.organizer.R;

public class LinedEditText extends AppCompatEditText {

    private Rect mRect;
    private Paint mPaint;

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorEditTextLine, null));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = mRect;
        Paint paint = mPaint;

        int baseline = getLineBounds(0, rect);
        for (int i = 0; i < 32; i++) {

            canvas.drawLine(rect.left, baseline + 1, rect.right, baseline + 1, paint);
            baseline += getLineHeight();
        }

        super.onDraw(canvas);
    }
}

