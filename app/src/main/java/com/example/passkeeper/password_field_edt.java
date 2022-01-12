package com.example.passkeeper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class password_field_edt extends AppCompatEditText {
    Drawable mCheckBtnImg;
    Drawable mEyeBtnImg;
    Drawable mCopyBtnImg;

    private void init() {
        mCheckBtnImg = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_check_opaque, null);
        mEyeBtnImg = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_eye_opaque, null);
        mCopyBtnImg = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_copy_opaque, null);

        // TODO: If these buttons is tapped, do something.
    }
    public password_field_edt(@NonNull Context context) {
        super(context);
        init();
    }

    public password_field_edt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public password_field_edt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
