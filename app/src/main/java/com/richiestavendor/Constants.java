package com.richiestavendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static String NAMESPACE = "http://tempuri.org/";
    public static String URL = "http://delivery.mriat.com/Service.asmx";///MyService
    public static String SOAP_ACTION = "http://tempuri.org/";
    public static String ImageURl = "http://delivery.mriat.com/dist/img/";
    public static String SUBJECT_URL = "mriat.com/User/view.aspx?PID=";

    public static void setEmpty(ArrayList<HashMap<String, String>> listHM){
        for (Map<String, String> map : listHM){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals("anyType{}")) {
                    entry.setValue("");
                }

            }

        }
    }

}
