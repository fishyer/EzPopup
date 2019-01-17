package com.ezbuy.library.ezpopup;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * author : yutianran
 * time   : 2019/01/17
 * desc   : Fix PopupWindow的系统bug 参考https://blog.csdn.net/zhihui_520/article/details/79097161
 * version: 1.0
 */
public class FixedPopupWindow extends PopupWindow {

    public FixedPopupWindow(View popupView) {
        super(popupView);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
            super.showAsDropDown(anchor, xoff, yoff);
        } else {
            super.showAsDropDown(anchor, xoff, yoff);
        }
    }
}
