package net.pside.android.outlineprovider.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstantStateCompat {
    public static Bitmap getBitmap(Drawable.ConstantState state, Bitmap altValue) {
        try {
            Method method = state.getClass().getMethod("getBitmap", (Class<?>[]) null);
            Object invoke = method.invoke(state);
            return (Bitmap) invoke;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return altValue;
    }
}
