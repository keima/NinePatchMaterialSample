package net.pside.android.outlineprovider.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import net.pside.android.outlineprovider.library.NinePatchViewOutlineProvider;

public class MessageLayout extends FrameLayout {
    private static final String TAG = "MessageLayout";

    // xmlから呼ばれる
    public MessageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MessageLayout(): attrs:   " + attrs.toString());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setupView();
    }

    private void setupView() {
        setBackgroundResource(R.drawable.ic_message);
        setElevation(20);
        setOutlineProvider(new NinePatchViewOutlineProvider());
    }
}
