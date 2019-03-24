package com.timkhakimov.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Timur Khakimov on 10.02.2018.
 */

public class Permissions {

    private final static String TAG = Permissions.class.toString();

    public static void request(Fragment fragment, String permissions, int code, Callback function) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M || isPermissionGranted(fragment.getContext(), permissions)) {
            function.execute();
        } else {
            fragment.requestPermissions(new String[]{permissions}, code);
        }
    }

    public static void request(Activity activity, String permissions, int code, Callback function) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M || isPermissionGranted(activity, permissions)) {
            function.execute();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permissions}, code);
        }
    }

    private static boolean isPermissionGranted(Context context, String permissions) {
        return ContextCompat.checkSelfPermission(context, permissions) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * call this method from Activity.onRequestPermissionsResult and Fragment.onRequestPermissionsResult
     *
     * @param requestCode
     * @param code
     * @param grantResults
     * @param granted
     * @param notGranted
     */
    public static void handleResponse(int requestCode, int code, int[] grantResults, Callback granted, Callback notGranted) {
        if (requestCode != code) {
            return;
        }
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "granted");
            granted.execute();
        } else {
            Log.d(TAG, "not granted");
            notGranted.execute();
        }
    }

    public interface Callback {
        void execute();
    }
}
