package com.ezbuy.library.ezpopup;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

/**
 * author : yutianran
 * time   : 2019/01/17
 * desc   :
 * version: 1.0
 */
public class ScreenUtil {

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.widthPixels;

    }

    /**
     * 获得屏幕区域高度  （状态栏 + 标题栏 + 内容 + 导航栏）
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获得应用程序区域高度 （标题栏 + 内容）
     * 另外 受 软键盘 影响
     *
     * @return
     */
    @Deprecated
    public static int getWindowRectHeight(FragmentActivity fragmentActivity) {
        Rect outRect = new Rect();
        fragmentActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.height();
    }

    public static int getWindowRectHeight() {
        return getScreenHeight() - getStatusBarHeight() - getNavigationBarHeight();
    }

    /**
     * 获得应用布局区域高度 （内容）
     *
     * @return
     */
    @Deprecated
    public static int getDrawingRectHeight(FragmentActivity fragmentActivity) {
        Rect outRect = new Rect();
        fragmentActivity.getWindow().findViewById(android.R.id.content).getDrawingRect(outRect);
//        fragmentActivity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        return outRect.height();
    }


    /**
     * 获得状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 获得导航栏高度
     *
     * @return
     */
    public static int getNavigationBarHeight() {
        int navigationBarHeight = 0;
        int resourceId = Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }
}
