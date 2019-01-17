package com.ezbuy.library.ezpopup;

import android.app.Activity;
import android.support.annotation.ColorRes;

/**
 * author : yutianran
 * time   : 2019/01/17
 * desc   :
 * version: 1.0
 */
public class EzPopup {

    private Activity activity;
    private int contentId;
    @ColorRes
    private int backgroundColor = -1;
    private float windowAlpha = 1.0f;
    private int animationStyle = -1;
    private PopupListener showListener;
    private PopupListener hideListener;
    private PopupTransformer popupTransformer;

    public static EzPopup newInstance() {
        return new EzPopup();
    }

    public PopupWindowHolder build() {
        return new PopupWindowHolder(this);
    }

    public EzPopup setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public EzPopup setContentId(int contentId) {
        this.contentId = contentId;
        return this;
    }

    public EzPopup setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public EzPopup setShowListener(PopupListener showListener) {
        this.showListener = showListener;
        return this;
    }

    public EzPopup setHideListener(PopupListener hideListener) {
        this.hideListener = hideListener;
        return this;
    }

    public EzPopup setPopupTransformer(PopupTransformer popupTransformer) {
        this.popupTransformer = popupTransformer;
        return this;
    }

    public EzPopup setWindowAlpha(float windowAlpha) {
        this.windowAlpha = windowAlpha;
        return this;
    }

    public float getWindowAlpha() {
        return windowAlpha;
    }

    public Activity getActivity() {
        return activity;
    }

    public int getContentId() {
        return contentId;
    }

    public PopupListener getShowListener() {
        return showListener;
    }

    public PopupListener getHideListener() {
        return hideListener;
    }

    public PopupTransformer getPopupTransformer() {
        return popupTransformer;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getAnimationStyle() {
        return animationStyle;
    }

    public EzPopup setAnimationStyle(int animationStyle) {
        this.animationStyle = animationStyle;
        return this;
    }

}
