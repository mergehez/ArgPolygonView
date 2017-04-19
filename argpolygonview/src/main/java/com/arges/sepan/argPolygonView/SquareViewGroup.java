package com.arges.sepan.argPolygonView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

abstract class SquareViewGroup extends ViewGroup {
    public SquareViewGroup(Context context) {
        super(context);
    }

    public SquareViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }
}
