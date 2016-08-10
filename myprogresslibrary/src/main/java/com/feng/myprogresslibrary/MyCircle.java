package com.feng.myprogresslibrary;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by bigwen on 2016/1/14.
 */
public class MyCircle extends View {
    Paint paint;
    int maxRadius = 16;
    ValueAnimator valueAnimator;
    float radiu = 10;
    int color = Color.RED;
    int width;
    int height;
    float pi2;
    float r;
    int duration=1000;
    boolean isStop=true;

    public MyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCircle(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        //计算出可用绘图的区域
        int availableWidth = measuredWidth - paddingLeft - paddingRight;
        int availableHeight = measuredHeight - paddingTop - paddingBottom;

        width = availableWidth / 2;
        height = availableHeight / 2;
        pi2 = 2 * (float) Math.PI;
        r = width - maxRadius;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle((float) (width + r * Math.sin(0)), (float) (height + r * Math.cos(0)), f(radiu + 0), paint);
        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8)), (float) (height + r * Math.cos(pi2 / 8)), f(radiu + 2), paint);
        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8 * 2)), (float) (height + r * Math.cos(pi2 / 8 * 2)), f(radiu + 4), paint);
        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8 * 3)), (float) (height + r * Math.cos(pi2 / 8 * 3)), f(radiu + 6), paint);

        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8 * 4)), (float) (height + r * Math.cos(pi2 / 8 * 4)), f(radiu + 8), paint);
        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8 * 5)), (float) (height + r * Math.cos(pi2 / 8 * 5)), f(radiu + 10), paint);
        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8 * 6)), (float) (height + r * Math.cos(pi2 / 8 * 6)), f(radiu + 12), paint);
        canvas.drawCircle((float) (width + r * Math.sin(pi2 / 8 * 7)), (float) (height + r * Math.cos(pi2 / 8 * 7)), f(radiu + 14), paint);

        if (null!=valueAnimator&&valueAnimator.isRunning()) {
            radiu = (float) valueAnimator.getAnimatedValue();
            invalidateView();
        }

    }

    /**
     * 开启动画
     */
    public void startAnimator() {
        try {
            stopAnimator();

            isStop=false;
            if (valueAnimator == null) {
                valueAnimator = ValueAnimator.ofFloat(0, maxRadius);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setDuration(duration);
                valueAnimator.start();
            } else {
                valueAnimator.start();
            }

            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.e("MyCircle", "开始");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e("MyCircle", "结束");
                    if (!isStop) {
                        startAnimator();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.e("MyCircle", "取消");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.e("MyCircle", "重复");
                }
            });
            invalidateView();
        } catch (Exception e) {
            Log.e("dialog", null == e ? "" : e.getMessage());
        }

    }


    /**
     * 停止动画
     */
    public void stopAnimator() {
        try {
            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.cancel();
                isStop=true;
            }
            valueAnimator=null;
        } catch (Exception e) {
            Log.e("dialog", null == e ? "" : e.getMessage());
        }
    }

    /**
     * 分别处理
     * @param x
     * @return
     */
    private float f(float x) {
        if (x <= maxRadius / 2) {
            return x;
        } else if (x < maxRadius) {
            return maxRadius - x;
        } else if (x < maxRadius * 3 / 2) {
            return x - maxRadius;
        } else {
            return 2 * maxRadius - x;
        }
    }


    public int getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
        this.maxRadius = maxRadius;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void invalidateView(){
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
