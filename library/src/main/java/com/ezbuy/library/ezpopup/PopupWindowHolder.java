package com.ezbuy.library.ezpopup;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * author : yutianran
 * time   : 2019/01/17
 * desc   : 参考Android技能树 — PopupWindow小结 https://juejin.im/post/5b88df6fe51d4538826f72ce
 * version: 1.0
 */
public class PopupWindowHolder {

    private EzPopup ezPopup;
    private FixedPopupWindow popupWindow;
    private View popupView;

    public PopupWindowHolder(final EzPopup ezPopup) {
        this.ezPopup = ezPopup;
        Activity activity = ezPopup.getActivity();
        popupView = LayoutInflater.from(activity).inflate(ezPopup.getContentId(), null);
        //使用Fix 7.0 Bug的自定义PopupWindow，设置contentView, 宽和高,获取焦点能力
        popupWindow = new FixedPopupWindow(popupView);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        // 需要设置一下PopupWindow背景，点击外边消失才起作用
        if (ezPopup.getBackgroundColor() == -1) {
            //默认设置背景透明
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            int color = activity.getResources().getColor(ezPopup.getBackgroundColor());
            popupWindow.setBackgroundDrawable(new ColorDrawable(color));
        }
        // 点击窗外可取消
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        // 设置pop被键盘顶上去，而不是遮挡
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //添加默认的动画效果
        int animationStyle = ezPopup.getAnimationStyle();
        if (animationStyle != -1) {
            popupWindow.setAnimationStyle(animationStyle);
        }
        //设置是否允许PopupWindow的范围超过屏幕范围
        popupWindow.setClippingEnabled(true);
        //自定义的配置将覆盖默认的配置
        PopupTransformer popupTransformer = ezPopup.getPopupTransformer();
        if (popupTransformer != null) {
            popupWindow = popupTransformer.transform(popupWindow);
        }
    }

    private void changeWindowAlpha(float alpha) {
        Window window = ezPopup.getActivity().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = alpha;
        window.setAttributes(params);
    }

    private void bindListener() {
        //设置窗口透明度
        changeWindowAlpha(ezPopup.getWindowAlpha());
        //设置显示和隐藏的监听
        if (ezPopup.getShowListener() != null) {
            ezPopup.getShowListener().onCall(popupWindow, popupView);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlpha(1.0f);
                if (ezPopup.getHideListener() != null) {
                    ezPopup.getHideListener().onCall(popupWindow, popupView);
                }
            }
        });
    }

    public void showCenter() {
        showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    public void showBottom() {
        showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
    }

    public void showTop() {
        showAtLocation(popupView, Gravity.TOP, 0, 0);
    }

    public void showAsDropDown(View anchor) {
        showAsDropDown(anchor, 0, 0, Gravity.TOP | Gravity.START);
    }

    /**
     * 最终调用的showAsDropDown
     *
     * @param anchor
     * @param xoff
     * @param yoff
     * @param gravity
     */
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (popupWindow.isShowing()) {
            return;
        }
        popupWindow.showAsDropDown(anchor, xoff, yoff, gravity);
        bindListener();
    }

    /**
     * 最终调用的showAtLocation
     *
     * @param parent
     * @param gravity
     * @param x
     * @param y
     */
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (popupWindow.isShowing()) {
            return;
        }
        popupWindow.showAtLocation(parent, gravity, x, y);
        bindListener();
    }

}
