package com.example.passkeeper.ui.utils;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.passkeeper.R;

import java.util.Objects;


public class PasswordEditText extends androidx.appcompat.widget.AppCompatEditText {


    private final static int EXTRA_TAPPABLE_AREA = 50;

    private int showIcon = R.drawable.ic_show_password;

    private int hideIcon = R.drawable.ic_hide_password;

    private Drawable showIconDrawable;

    private Drawable hideIconDrawable;

    private boolean isVisibility;

    private boolean isShowIcon;

    private boolean isHoverShow;

    private boolean isHandleHoverEvent;

    public PasswordEditText(Context context) {
        this(context, null);
        init(null, 0);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, defStyleAttr, 0);
            try {
                showIcon = styledAttributes.getResourceId(R.styleable.PasswordEditText_showIcon, showIcon);
                hideIcon = styledAttributes.getResourceId(R.styleable.PasswordEditText_hideIcon, hideIcon);
                isHoverShow = styledAttributes.getBoolean(R.styleable.PasswordEditText_hoverShow, false);
            } finally {
                styledAttributes.recycle();
            }
        }

        showIconDrawable = Objects.requireNonNull(ContextCompat.getDrawable(getContext(), showIcon)).mutate();
        hideIconDrawable = Objects.requireNonNull(ContextCompat.getDrawable(getContext(), hideIcon)).mutate();

        addTextChangedListener(watcher);

    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0) {
                setCompoundDrawables(null, null, null, null);
                showPassword(true);
            } else {
                isVisibility = false;
                handlePasswordInput();
                showPassword(false);
            }
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isShowIcon) {
            return super.onTouchEvent(event);
        } else {
            final Rect bounds = showIconDrawable.getBounds();
            final int x = (int) event.getX();
            int iconArea = getRight() - bounds.width() - EXTRA_TAPPABLE_AREA;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (isHoverShow) {
                        if (x >= iconArea) {
                            togglePasswordIcon();
                            event.setAction(MotionEvent.ACTION_CANCEL);
                            isHandleHoverEvent = true;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isHandleHoverEvent || (x >= iconArea)) {
                        togglePasswordIcon();
                        event.setAction(MotionEvent.ACTION_CANCEL);
                        isHandleHoverEvent = false;
                    }
                    break;
            }
            return super.onTouchEvent(event);
        }
    }


    private void showPassword(boolean showIcon) {
        if (showIcon) {
            Drawable drawable = isVisibility ? hideIconDrawable : showIconDrawable;
            isShowIcon = true;
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        } else {

            setCompoundDrawables(null, null, null, null);
            isShowIcon = false;
        }
    }

    private void togglePasswordIcon() {
        isVisibility = !isVisibility;
        handlePasswordInput();
        showPassword(true);
    }

    private void handlePasswordInput() {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (isVisibility) {
            setTransformationMethod(null);
        } else {
            setTransformationMethod(PasswordTransformationMethod.getInstance());

        }
        setSelection(selectionStart, selectionEnd);

    }

}
