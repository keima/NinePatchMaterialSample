package net.pside.android.outlineprovider.library.util;

import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NinePatchDrawableCompat {
    public static Bitmap getBitmap(NinePatchDrawable drawable, Bitmap altValue) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            NinePatch ninePatch = getNinePatch(drawable, null);
            if (ninePatch != null) {
                return ninePatch.getBitmap();
            }
        } else {
            Drawable.ConstantState state = drawable.getConstantState();
            try {
                Method method = state.getClass().getMethod("getBitmap", (Class<?>[]) null);
                Object invoke = method.invoke(state);
                return (Bitmap) invoke;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return altValue;
    }

    public static NinePatch getNinePatch(NinePatchDrawable drawable, NinePatch defValue) {
        try {
            Field field = drawable.getClass().getDeclaredField("mNinePatch");
            field.setAccessible(true);
            return (NinePatch) field.get(drawable);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return defValue;
    }
}
