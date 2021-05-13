package com.joseortale.ortalesoft.simpledagger.utils;

import android.view.View;

public class ViewHelper {
    public static void setOnLoading(View progressBar, View rootLayout) {
        progressBar.setVisibility(View.VISIBLE);
        rootLayout.setVisibility(View.GONE);
    }

    public static void setOnFinishedLoading(View progressBar, View rootLayout) {
        progressBar.setVisibility(View.GONE);
        rootLayout.setVisibility(View.VISIBLE);
    }
}
