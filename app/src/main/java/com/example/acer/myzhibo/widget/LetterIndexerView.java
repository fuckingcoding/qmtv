package com.example.acer.myzhibo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import mdzz.com.first_of_mdzz.R;

/**
 * Created by StevenWang on 16/10/28.
 */

public class LetterIndexerView extends View {
    private String[] mArrLetters = new String[]{"热门","#", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"};
    private int mLineHeight = 0;
    private Paint mPaint = null;
    private TextView textView_dialog;
    private float mDensity = 0;
    private OnLetterClickedListener mListener = null;

    public LetterIndexerView(Context context) {
        super(context);
        init(null, 0);
    }

    public LetterIndexerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LetterIndexerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        mDensity = getResources().getDisplayMetrics().density;

        mPaint = new Paint();
        Log.i("TAG", "----->>>> onDraw: " + mPaint.hashCode());

        //paint.setAntiAlias(true);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(14 * mDensity);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int widthPx = (int) (35 * mDensity);
        setMeasuredDimension(widthPx, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewWidth = getWidth();
        int viewHeight = getHeight();
        mLineHeight = viewHeight / mArrLetters.length;

        for (int i = 0; i < mArrLetters.length; i++) {
            int textWidth = (int) mPaint.measureText(mArrLetters[i]);
            canvas.drawText(mArrLetters[i], (viewWidth - textWidth) / 2, mLineHeight * (i + 1),
                    mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        //获取点击的位置对应的索引下标
        int position = (int) (y / mLineHeight);
        if (position>=0 && position < mArrLetters.length) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    setBackgroundColor(Color.TRANSPARENT);
                    if (textView_dialog != null && textView_dialog.getVisibility() == VISIBLE) {
                        textView_dialog.setVisibility(GONE);
                    }
                    break;
                default:   //Color.parseColor("#aaaaaa")
                    setBackgroundColor(getResources().getColor(R.color.gray));
                    if (textView_dialog != null) {
                        textView_dialog.setVisibility(VISIBLE);
                        textView_dialog.setText(mArrLetters[position]);
                    }
                    //接口回调
                    if (mListener != null) {
                        mListener.onClick(mArrLetters[position]);
                    }
                    break;
            }
        } else {
            setBackgroundColor(Color.TRANSPARENT);
            if (textView_dialog != null && textView_dialog.getVisibility() == VISIBLE) {
                textView_dialog.setVisibility(GONE);
            }
        }
        return true;
    }

    public interface OnLetterClickedListener {
        void onClick(String letter);
    }

    public void setOnLetterClickedListener(TextView _textView_dialog, OnLetterClickedListener
            _listener) {
        this.textView_dialog = _textView_dialog;
        this.mListener = _listener;
    }
}
