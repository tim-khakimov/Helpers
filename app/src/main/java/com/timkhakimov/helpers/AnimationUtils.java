package com.timkhakimov.helpers;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

/**
 * Created by Timur Khakimov on 10.02.2018.
 */

public class AnimationUtils {

    public static void expand(final View view) {
        view.measure(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = view.getMeasuredHeight();
        int duration = ((int)(targetHeight/view.getContext().getResources().getDisplayMetrics().density));
        expand(view, duration, targetHeight, null);
    }

    public static void expand(final View v, int duration) {
        expand(v, duration, null);
    }

    public static void expand(final View view, int duration, Animation.AnimationListener listener) {
        view.measure(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = view.getMeasuredHeight();
        expand(view, duration, targetHeight, listener);
    }

    public static  void expand(final View view, int duration, final int targetHeight, Animation.AnimationListener listener) {
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);
        Animation expandAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.getLayoutParams().height = interpolatedTime == 1
                        ? FrameLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        expandAnimation.setDuration(duration);
        if(listener!=null) {
            expandAnimation.setAnimationListener(listener);
        }
        view.startAnimation(expandAnimation);
    }

    public static void collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();
        int duration = ((int)(initialHeight/view.getContext().getResources().getDisplayMetrics().density));
        collapse(view, duration, initialHeight, null);
    }

    public static void collapse(final View v, int duration) {
        collapse(v, duration, null);
    }

    public static void collapse(final View view, int duration, Animation.AnimationListener listener) {
        final int initialHeight = view.getMeasuredHeight();
        collapse(view, duration, initialHeight, listener);
    }

    public static void collapse(final View view, int duration, final int initialHeight, Animation.AnimationListener listener) {
        Animation collapseAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        collapseAnimation.setDuration(duration);
        if(listener!=null) {
            collapseAnimation.setAnimationListener(listener);
        }
        view.startAnimation(collapseAnimation);
    }

    public static void rotate(View view, int duration, float fromDegrees, float toDegrees){
        rotate(view, duration, fromDegrees, toDegrees, null);
    }

    public static void rotate(View view, int duration, float fromDegrees, float toDegrees, Animation.AnimationListener listener){
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(duration);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setFillAfter(true);
        if(listener!=null) {
            rotateAnimation.setAnimationListener(listener);
        }
        view.startAnimation(rotateAnimation);
    }
}
