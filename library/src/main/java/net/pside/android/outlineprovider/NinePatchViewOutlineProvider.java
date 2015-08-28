package net.pside.android.outlineprovider;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import net.pside.android.outlineprovider.util.BitmapCompat;
import net.pside.android.outlineprovider.util.ConstantStateCompat;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class NinePatchViewOutlineProvider extends ViewOutlineProvider {

    @Override
    public void getOutline(View view, Outline outline) {
        Drawable background = view.getBackground();
        if (background != null && background instanceof NinePatchDrawable) {
            NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) background;
            Drawable.ConstantState constantState = ninePatchDrawable.getConstantState();
            Bitmap bitmap = ConstantStateCompat.getBitmap(constantState, null);

            if (bitmap != null) {
                Rect opticalRect = BitmapCompat.getOpticalRect(bitmap, new Rect(0, 0, 0, 0));
                Rect bounds = background.getBounds();
                float outlineRadius = BitmapCompat.getOutlineRadius(bitmap, 0);
                float outlineAlpha = BitmapCompat.getOutlineAlpha(bitmap, 0);

                outline.setRoundRect(
                        bounds.left + opticalRect.left,
                        bounds.top + opticalRect.top,
                        bounds.right - opticalRect.right,
                        bounds.bottom - opticalRect.bottom,
                        outlineRadius
                );
                float alpha = outlineAlpha * (ninePatchDrawable.getAlpha() / 255.0f);
                outline.setAlpha(alpha);
            }
        }
    }

}