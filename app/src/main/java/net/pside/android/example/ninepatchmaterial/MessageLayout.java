package net.pside.android.example.ninepatchmaterial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

public class MessageLayout extends FrameLayout {
    private static final String TAG = "MessageLayout";

    // xmlから呼ばれる
    public MessageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG + "#MessageLayout()", "attrs:   " + attrs.toString());
    }

    public MessageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG + "#MessageLayout()", "attrs:   " + attrs.toString());
        Log.d(TAG + "#MessageLayout()", "defAttr: " + defStyleAttr);
        Log.d(TAG + "#MessageLayout()", "defRes:  " + defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setupView();
    }

    private void setupView() {
        setBackgroundResource(R.drawable.ic_message);
        setElevation(20);

        if (!isInEditMode()) {
            setOutlineProvider(new MyOutlineProvider());
        }
    }

    private class MyOutlineProvider extends ViewOutlineProvider {
        @Override
        public void getOutline(View view, Outline outline) {
            Drawable background = view.getBackground();
            if (background != null) {
                NinePatchDrawable ninePatchDrawable = (NinePatchDrawable)background;
                Drawable.ConstantState constantState = ninePatchDrawable.getConstantState();
                Bitmap bitmap = ConstantStateCompat.getBitmap(constantState);

                if (bitmap != null) {
                    Rect opticalRect = BitmapCompat.getOpticalRect(bitmap);
                    Rect bounds = background.getBounds();
                    float outlineRadius = BitmapCompat.getOutlineRadius(bitmap);
                    float outlineAlpha = BitmapCompat.getOutlineAlpha(bitmap);

                    outline.setRoundRect(bounds.left + opticalRect.left,
                            bounds.top + opticalRect.top,
                            bounds.right - opticalRect.right,
                            bounds.bottom - opticalRect.bottom,
                            outlineRadius);
                    float hoge = outlineAlpha * (ninePatchDrawable.getAlpha() / 255.0f);
                    outline.setAlpha(hoge);
                }
            }
        }
    }
}
