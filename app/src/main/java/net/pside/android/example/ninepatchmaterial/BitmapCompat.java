package net.pside.android.example.ninepatchmaterial;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BitmapCompat {
    public static void getOpticalInsets(@NonNull Bitmap bitmap, @NonNull Rect outInsets) {
        try {
            Method method = bitmap.getClass().getMethod("getOpticalInsets", Rect.class);
            method.invoke(bitmap, outInsets);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
