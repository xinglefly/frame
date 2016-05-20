package com.bytetime.jrim.utils;

import android.widget.Toast;

import com.bytetime.jrim.JRIM;

public class ToastUtil {
    public static void showToast(String message, Object... args) {
        if(args != null) {
            message = String.format(message, args);
        }

        Toast.makeText(JRIM.getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String message, Object... args) {
        if(args != null) {
            message = String.format(message, args);
        }

        Toast.makeText(JRIM.getApplication(), message, Toast.LENGTH_SHORT).show();
    }
}
