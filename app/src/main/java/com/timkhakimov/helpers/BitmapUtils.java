package com.timkhakimov.helpers;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Timur Khakimov on 02.04.2019
 */
public class BitmapUtils {

    public static Bitmap getCroppedAndResizedBitmap(Bitmap bitmap, int viewWidth, int viewHeight) {
        Bitmap croppedBitmap = getCroppedBitmap(bitmap, viewWidth, viewHeight);
        int cropppedWidth = croppedBitmap.getWidth();
        if (cropppedWidth == viewWidth) {
            return croppedBitmap;
        }
        return getResizedBitmap(croppedBitmap, viewWidth, viewHeight);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public static Bitmap getCroppedBitmap(Bitmap bitmap, int viewWidth, int viewHeight) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        float originalRatio = (float) originalWidth / (float) originalHeight;
        float viewRatio = (float) viewWidth / (float) viewHeight;
        int startX = 0;
        int startY = 0;
        int targetWidth = 0;
        int targetHeight = 0;
        if (originalRatio == viewRatio) {
            return bitmap;
        } else if (originalRatio < viewRatio) {
            targetWidth = originalWidth;
            startX = 0;
            targetHeight = (int) ((float) targetWidth / viewRatio);
            startY = (originalHeight - targetHeight) / 2;
        } else {
            targetHeight = originalHeight;
            startY = 0;
            targetWidth = (int) (targetHeight * viewRatio);
            startX = (originalWidth - targetWidth) / 2;
        }
        Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, startX, startY, targetWidth, targetHeight);
        return croppedBitmap;
    }
}
