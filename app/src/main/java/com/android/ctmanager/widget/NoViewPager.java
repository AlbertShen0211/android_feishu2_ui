package com.android.ctmanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoViewPager extends ViewPager {

    private boolean canSwipe = true;
    public NoViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    //是否禁止滑动
    public void setCanSwipe(boolean canSwipe)
    {
        this.canSwipe = canSwipe;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canSwipe && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canSwipe && super.onInterceptTouchEvent(ev);
    }
}