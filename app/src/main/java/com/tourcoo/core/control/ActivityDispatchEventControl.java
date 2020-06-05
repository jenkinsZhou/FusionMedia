package com.tourcoo.core.control;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;


/**
 * @Function: Activity 事件派发--必须继承自{@link com.aries.library.fast.basis.BasisActivity}
 * Activity 必须在前台
 * @Description:
 */
public interface ActivityDispatchEventControl {

    /**
     * {@link BasisActivity#dispatchTouchEvent(MotionEvent)}
     *
     * @param activity
     * @param event
     * @return
     */
    boolean dispatchTouchEvent(Activity activity, MotionEvent event);

    /**
     * {@link BasisActivity#dispatchGenericMotionEvent(MotionEvent)}
     *
     * @param activity
     * @param event
     * @return
     */
    boolean dispatchGenericMotionEvent(Activity activity, MotionEvent event);

    /**
     * {@link BasisActivity#dispatchKeyEvent(KeyEvent)}
     *
     * @param activity
     * @param event
     * @return
     */
    boolean dispatchKeyEvent(Activity activity, KeyEvent event);

    /**
     * {@link BasisActivity#dispatchKeyShortcutEvent(KeyEvent)}
     *
     * @param activity
     * @param event
     * @return
     */
    boolean dispatchKeyShortcutEvent(Activity activity, KeyEvent event);

    /**
     * {@link BasisActivity#dispatchTrackballEvent(MotionEvent)}
     *
     * @param activity
     * @param event
     * @return
     */
    boolean dispatchTrackballEvent(Activity activity, MotionEvent event);

    /**
     * {@link BasisActivity#dispatchPopulateAccessibilityEvent(AccessibilityEvent)}
     *
     * @param activity
     * @param event
     * @return
     */
    boolean dispatchPopulateAccessibilityEvent(Activity activity, AccessibilityEvent event);
}
