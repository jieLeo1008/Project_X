package com.jieleo.projecta.custromview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.PopupWindow;

/**
 * Created by jie on 2017/2/20.
 */

public class MyPopupWindow extends PopupWindow {
    public MyPopupWindow(Context context) {
        super(context);
    }

    public MyPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
