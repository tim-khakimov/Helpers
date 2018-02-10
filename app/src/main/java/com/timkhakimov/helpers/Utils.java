package com.timkhakimov.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Timur Khakimov on 10.02.2018.
 */

public class Utils {

    public static void openLink(Context context, String link){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        context.startActivity(intent);
    }

    public static void mailTo(Context context, String recipient){
        try {
            Intent intent_email = new Intent(Intent.ACTION_SENDTO);
            intent_email.setData(Uri.parse("mailto:" + recipient));
            context.startActivity(intent_email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callTo(Context context, String number){
        try {
            Intent intent_call = new Intent(Intent.ACTION_DIAL);
            intent_call.setData(Uri.parse("tel:" + number.replaceAll("[^\\d]", "")));
            context.startActivity(intent_call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }
}
