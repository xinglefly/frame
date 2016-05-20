package com.bytetime.jrim.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bytetime.jrim.utils.LoadingDialog;

public abstract class BaseLinearLayoutView extends LinearLayout {

    protected LoadingDialog loadingDialog;

    public BaseLinearLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupInject();
        loadingDialog = new LoadingDialog(context);
    }

    protected abstract void setupInject();

    public void showLoading() {
        loadingDialog.show();
    }

    public void dismissLoading() {
        loadingDialog.dismiss();
    }

}
