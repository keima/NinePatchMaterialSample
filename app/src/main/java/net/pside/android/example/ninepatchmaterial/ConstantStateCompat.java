package net.pside.android.example.ninepatchmaterial;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstantStateCompat {
    public static Bitmap getBitmap(Drawable.ConstantState state) {
        try {
            Method method = state.getClass().getMethod("getBitmap", (Class)null);
            Object invoke = method.invoke(state);

            return (Bitmap)invoke;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
