# EzPopup

处理PopupWindow在Android 7.x中兼容性问题的示例

PopupWindow 中的 showAsDropDown(View anchor) 用于在指定锚点View下方显示 PopupWindow，在Android 7.0 (api<=23) 以前是没什么问题的，但是在Android 7.x系统上，会在某些情况下出现兼容问题：

如果指定 PopupWindow 的高度为 MATCH_PARENT，调用 showAsDropDown(View anchor) 时，在 7.0 之前，会在锚点 anchor 下边缘到屏幕底部之间显示 PopupWindow；而在 7.0、7.1 系统上的 PopupWindow 会占据整个屏幕（除状态栏之外）。

如果指定 PopupWindow 的高度为 WRAP_CONTENT, 调用 showAsDropDown(View anchor) 时，便不会出现兼容性的问题。

如果指定 PopupWindow 的高度为自定义的值height，调用 showAsDropDown(View anchor)时， 如果 height > 锚点 anchor 下边缘与屏幕底部的距离， 则还是会出现7.0、7.1上显示异常的问题；否则，不会出现该问题。可以看出，情况1和2是情况3的特例。

#### 使用示例

1. 创建PopupWindow

```
PopupWindowHolder holder = EzPopup.newInstance()
                .setActivity(this)//设置Activity
                .setContentId(R.layout.popupview)//设置内容layout
                .setWindowAlpha(0.4f)//设置背景变暗
                .setShowListener(new PopupListener() {
                    @Override
                    public void onCall(PopupWindow popupWindow, View view) {
                        //显示的时候，在这里处理UI事件
                        log("onShow");
                    }
                })
                .setHideListener(new PopupListener() {
                    @Override
                    public void onCall(PopupWindow popupWindow, View view) {
                        //消失的时候
                        log("onHide");
                    }
                })
                .build();

```

2. 显示PopupWindow
```]
//拓展的方法
holder.showAsDropDown(anchor);//锚点下面
holder.showBottom();//底部显示
holder.showCenter();//中心显示
holder.showTop();//顶部显示

//默认的方法
showAsDropDown(View anchor, int xoff, int yoff, int gravity);
showAtLocation(View parent, int gravity, int x, int y);
```
