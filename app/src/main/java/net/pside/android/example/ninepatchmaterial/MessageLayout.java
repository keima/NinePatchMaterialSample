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

                // FIXME: getNinePatchInsetsのほうが良かった
                Rect opticalInsets = new Rect();
                if (bitmap != null) {
                    BitmapCompat.getOpticalInsets(bitmap, opticalInsets);
                    Rect bounds = background.getBounds();

                    outline.setRoundRect(bounds.left + opticalInsets.left,
                            bounds.top + opticalInsets.top,
                            bounds.right - opticalInsets.right,
                            bounds.bottom - opticalInsets.bottom,
                            20);
                    outline.setAlpha(1.0f);
                }
            }
//                background.getOutline(outline);
//
//                ((NinePatchDrawable)background).
//
//
//                final Rect bounds = background.getBounds();
//                if (bounds.isEmpty()) return;
//
//                final Rect outlineInsets = background..outlineRect;
//                outline.setRoundRect(bounds.left + outlineInsets.left,
//                        bounds.top + outlineInsets.top,
//                        bounds.right - outlineInsets.right,
//                        bounds.bottom - outlineInsets.bottom,
//                        insets.outlineRadius);
//
//                if (ninePatchState != null) {
//                    NinePatch.InsetStruct insets = mNinePatchState.getBitmap().getNinePatchInsets();
//                    if (insets != null) {
//                        final Rect outlineInsets = insets.outlineRect;
//                        outline.setRoundRect(bounds.left + outlineInsets.left,
//                                bounds.top + outlineInsets.top,
//                                bounds.right - outlineInsets.right,
//                                bounds.bottom - outlineInsets.bottom,
//                                insets.outlineRadius);
//                        outline.setAlpha(insets.outlineAlpha * (getAlpha() / 255.0f));
//                        return;
//                    }
//                }
//                super.getOutline(outline);
//
//            } else {
//                outline.setRect(0, 0, view.getWidth(), view.getHeight());
//                outline.setAlpha(1.0f);
//            }

            // BACKGROUND.getOutline(view, outline);


        }
    }
}
