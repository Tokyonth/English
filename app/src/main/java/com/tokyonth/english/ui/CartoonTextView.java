package com.tokyonth.english.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.tokyonth.english.initializeApp;

public class CartoonTextView extends android.support.v7.widget.AppCompatTextView {

    public CartoonTextView(Context context) {
        super(context);
        setTypeface(initializeApp.getInstance().getTypeface());
    }

    public CartoonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(initializeApp.getInstance().getTypeface());
    }

    public CartoonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(initializeApp.getInstance().getTypeface());
    }

}

