package com.richiestavendor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class

Constants {

    public static String NAMESPACE = "http://tempuri.org/";
    public static String URL = "http://delivery.rkesta.info/Service.asmx";///MyService
    public static String IMAGE_URL = "http://cp.rkesta.com/prdPic/";
    public static String SOAP_ACTION = "http://tempuri.org/";

    public static void setEmpty(List<HashMap<String, String>> listHM){
        for (Map<String, String> map : listHM){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals("anyType{}")) {
                    entry.setValue("");
                }

            }

        }
    }

    public static Map<String, Object> toMap( Object object ) throws Exception
    {
        Map<String, Object> map = new LinkedHashMap<>();
        for ( Field field : object.getClass().getDeclaredFields() )
        {
            field.setAccessible( true );
            map.put( field.getName(), field.get( object ) );
        }
        return map;
    }


    public static boolean requestStoragePermission(Activity activity) {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;

        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //If the user has denied the permission previously your code will come to this block
                //Here you can explain why you need this permission
                //Explain here why you need this permission
                Toast.makeText(activity, activity.getResources().getString(R.string.storage_per), Toast.LENGTH_SHORT).show();

            }
            //And finally ask for the permission
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        }
    }
}
