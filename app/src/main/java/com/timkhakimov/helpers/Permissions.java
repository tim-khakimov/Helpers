package com.timkhakimov.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Timur Khakimov on 10.02.2018.
 */

public class Permissions {

    private final static String TAG = Permissions.class.toString();

    public static void request(Activity activity, String permissions, int code, Callback function){
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(activity, permissions) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        permissions)) {

                } /*else*/ {
                    ActivityCompat.requestPermissions(activity, new String[]{ permissions}, code);
                }
            } else{
                function.execute();
            }
        } else {
            function.execute();
        }
    }

    public static void handleResponse(int requestCode, int code, int[] grantResults, Callback granted, Callback notGranted){
        if (requestCode == code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "granted");
                granted.execute();
            } else {
                Log.d(TAG, "not granted");
                notGranted.execute();
            }
        }
    }

    public interface Callback{
        public void execute();
    }
}
