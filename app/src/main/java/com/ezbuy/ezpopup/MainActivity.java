package com.ezbuy.ezpopup;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

import com.ezbuy.library.ezpopup.EzPopup;
import com.ezbuy.library.ezpopup.PopupWindowHolder;
import com.ezbuy.library.ezpopup.PopupListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View tv0;
    private PopupWindowHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv0 = findViewById(R.id.textView0);
        tv0.setOnClickListener(this);
        findViewById(R.id.textView1).setOnClickListener(this);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.textView3).setOnClickListener(this);

        log("Build.VERSION.SDK_INT:" + Build.VERSION.SDK_INT);
        getPopupWindowHolder();
    }

    private PopupWindowHolder getPopupWindowHolder() {
//        if (holder != null) {
//            return holder;
//        }
        holder = EzPopup.newInstance()
                .setActivity(this)
                .setContentId(R.layout.popupview)
                .setWindowAlpha(0.4f)
                .setShowListener(new PopupListener() {
                    @Override
                    public void onCall(PopupWindow popupWindow, View view) {
                        log("onShow");
                    }
                })
                .setHideListener(new PopupListener() {
                    @Override
                    public void onCall(PopupWindow popupWindow, View view) {
                        log("onHide");
                    }
                })
                .build();
        return holder;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView0:
                getPopupWindowHolder().showAsDropDown(tv0);
                break;
            case R.id.textView1:
                getPopupWindowHolder().showBottom();
                break;
            case R.id.textView2:
                getPopupWindowHolder().showCenter();
                break;
            case R.id.textView3:
                getPopupWindowHolder().showTop();
                break;
            default:
        }
    }

    private void log(String msg) {
        Log.d("EZPOP", msg);
    }
}
