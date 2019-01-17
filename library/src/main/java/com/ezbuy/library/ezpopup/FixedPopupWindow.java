package com.ezbuy.library.ezpopup;

import android.graphics.Rect;
import android.os.Build;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * author : yutianran
 * time   : 2019/01/17
 * desc   : Fix PopupWindow的系统bug 参考https://blog.csdn.net/zhihui_520/article/details/79097161
 * version: 1.0
 */
public class FixedPopupWindow extends PopupWindow {

    private static final int DEFAULT_ANCHORED_GRAVITY = Gravity.TOP | Gravity.START;

    public FixedPopupWindow(View popupView) {
        super(popupView);
    }

    @Override
    public void showAsDropDown(View anchor) {
        showAsDropDown(anchor, 0, 0);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        showAsDropDown(anchor, xoff, yoff, DEFAULT_ANCHORED_GRAVITY);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        //Build.VERSION_CODES.N api 24  android 7.0
        //Build.VERSION_CODES.N_MR1 api 25  android 7.1.1
        //Build.VERSION_CODES.O api 26  android 8.0
        if (Build.VERSION.SDK_INT == 24 || Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 26) {
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (super.getHeight() == ViewGroup.LayoutParams.MATCH_PARENT) {
                int screenHeight = ScreenUtil.getScreenHeight();
                super.setHeight(screenHeight - y - anchor.getHeight() - yoff);
            }
        }
        PopupWindowCompat.showAsDropDown(this, anchor, xoff, yoff, gravity);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void dismiss() {
        if (!isShowing()) {
            return;
        }
        super.dismiss();
    }
}
