package com.example.passkeeper.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class password_field_drawable extends Drawable {
    Drawable mCheckBtnImg;
    Drawable mEyeBtnImg;
    Drawable mCopyBtnImg;
    @Override
    public void draw(@NonNull Canvas canvas) {
        mCheckBtnImg.draw(canvas);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
