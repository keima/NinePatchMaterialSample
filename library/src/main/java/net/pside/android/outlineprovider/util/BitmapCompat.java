package net.pside.android.outlineprovider.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BitmapCompat {

    public static Rect getOpticalRect(@NonNull Bitmap bitmap, Rect altValue) {
        try {
            Method method = bitmap.getClass().getMethod("getNinePatchInsets", (Class<?>[]) null);
            Object invokedInsetStruct = method.invoke(bitmap);
            Field field = invokedInsetStruct.getClass().getField("opticalRect");
            return (Rect) field.get(invokedInsetStruct);
        } catch (NoSuchMethodException | InvocationTargetException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return altValue;
    }

    public static float getOutlineRadius(@NonNull Bitmap bitmap, float altValue) {
        try {
            Method method = bitmap.getClass().getMethod("getNinePatchInsets", (Class<?>[]) null);
            Object invokedInsetStruct = method.invoke(bitmap);
            Field field = invokedInsetStruct.getClass().getField("outlineRadius");
            return field.getFloat(invokedInsetStruct);
        } catch (NoSuchMethodException | InvocationTargetException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return altValue;
    }

    public static float getOutlineAlpha(@NonNull Bitmap bitmap, float altValue) {
        try {
            Method method = bitmap.getClass().getMethod("getNinePatchInsets", (Class<?>[]) null);
            Object invokedInsetStruct = method.invoke(bitmap);
            Field field = invokedInsetStruct.getClass().getField("outlineAlpha");
            return field.getFloat(invokedInsetStruct);
        } catch (NoSuchMethodException | InvocationTargetException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return altValue;
    }
}
