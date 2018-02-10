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

    public static void expand(final View v) {
        v.measure(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        int duration = ((int)(targetHeight/v.getContext().getResources().getDisplayMetrics().density));
        expand(v, duration, null);
    }

    public static void expand(final View v, int duration) {
        v.measure(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        expand(v, duration, null);
    }

    public static void expand(final View v, int duration, AnimationEndListener listener) {
        v.measure(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation expandAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? FrameLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
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
        v.startAnimation(expandAnimation);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        int duration = ((int)(initialHeight/v.getContext().getResources().getDisplayMetrics().density));
        collapse(v, duration, null);
    }

    public static void collapse(final View v, int duration) {
        collapse(v, duration, null);
    }

    public static void collapse(final View v, int duration, AnimationEndListener listener) {
        final int initialHeight = v.getMeasuredHeight();
        Animation collapseAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
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
        v.startAnimation(collapseAnimation);
    }


    public static void rotate(View view, int duration, float fromDegrees, float toDegrees){
        rotate(view, duration, fromDegrees, toDegrees, null);
    }

    public static void rotate(View view, int duration, float fromDegrees, float toDegrees, AnimationEndListener listener){
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
