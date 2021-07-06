package com.richiestavendor.Branches.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Branches.Contract;
import com.richiestavendor.Constants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BranchesModel implements Contract.Branches.Model {


    @Override
    public void getBranches(onFinishedListener onFinishedListener, String id) {

        new getData(onFinishedListener , id).execute();
    }

    @Override
    public void getBranchesDetails(onFinishedListener onFinishedListener, String id) {

        new getDataDetaisl(onFinishedListener , id).execute();
    }

    @Override
    public void getDeleteBranch(onFinishedListener onFinishedListener, String id , String id_details) {


        new delete(onFinishedListener ,id , id_details).execute();
    }


    public ArrayList<HashMap<String, String>> getData(String id) {
        String FName = "Selectsprk_SelectRK_BranchByStoreId";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="StoreID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(id);

        request.addProperty(propertyInfo);

        /** end send*/
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);

        /** if Double
         MarshalDouble md = new MarshalDouble();
         md.register(envelope);
         */

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);

        ArrayList<HashMap<String, String>> ArrayListHash = new ArrayList<HashMap<String, String>>();
        try {
            // Invole web service
            androidHttpTransport.call(Constants.SOAP_ACTION + FName, envelope);
            // Get the response

            SoapObject resultsString = (SoapObject) envelope.getResponse();
            //String response = resultsString.getProperty(0).toString();
            SoapObject Object1 = (SoapObject) resultsString.getProperty(1);
            SoapObject tables = (SoapObject) Object1.getProperty(0);



            String [] prop = new String[] {"NameAR", "NameEN", "Feild", "RK_StoresContact", "RK_Stores", "RK_branchNotes", "CreatedBy", "CreatedDate",
                    "ModifiedBy", "ModifiedDate"};
            SoapObject Objecttable;
            HashMap<String, String> map;
            for (int i = 0; i < tables.getPropertyCount(); i++) {
                Objecttable = (SoapObject) tables.getProperty(i);
                map = new HashMap<String, String>();
                for (String propr:prop) {
                    if(Objecttable.hasProperty(propr))
                        map.put(propr, Objecttable.getProperty(propr).toString());
                }
                ArrayListHash.add(map);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("poiuy", e.getMessage());
        }

        return ArrayListHash;
    }


    public ArrayList<HashMap<String, String>> getDataDetails(String id) {
        String FName = "Selectvwrk_RK_BranchAllDetails";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="ID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(id);

        request.addProperty(propertyInfo);

        /** end send*/
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);

        /** if Double
         MarshalDouble md = new MarshalDouble();
         md.register(envelope);
         */

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);

        ArrayList<HashMap<String, String>> ArrayListHash = new ArrayList<HashMap<String, String>>();
        try {
            // Invole web service
            androidHttpTransport.call(Constants.SOAP_ACTION + FName, envelope);
            // Get the response

            SoapObject resultsString = (SoapObject) envelope.getResponse();
            //String response = resultsString.getProperty(0).toString();
            SoapObject Object1 = (SoapObject) resultsString.getProperty(1);
            SoapObject tables = (SoapObject) Object1.getProperty(0);


            String [] prop = new String[] {"RKBranch_NameAR", "RKBranch_NameEN", "RKBranch_Feild", "RKBranch_RK_StoresContact", "RKBranch_RK_Stores",
                    "RKBranch_RK_branchNotes", "RKBranch_CreatedBy", "RKBranch_CreatedDate", "RKBranch_ID",
                    "RKBranch_ModifiedBy", "RKBranch_ModifiedDate", "RKBranchDetails_ID", "RKBranchDetails_Address", "CityNameArabic", "CityNameEnglish",
                    "RKBranchDetails_Phone", "RKBranchDetails_Cell", "RKBranchDetails_Email", "RKBranchDetails_StoreNotes",
                    "RKBranchDetails_Createdby", "RKBranchDetails_Longitude" , "RKBranchDetails_Latitude" , "CountryNameEnglish" , "CountryNameArabic" ,
            "RKBranchDetails_ID"};
            SoapObject Objecttable;
            HashMap<String, String> map;
            for (int i = 0; i < tables.getPropertyCount(); i++) {
                Objecttable = (SoapObject) tables.getProperty(i);
                map = new HashMap<String, String>();
                for (String propr:prop) {
                    if(Objecttable.hasProperty(propr))
                        map.put(propr, Objecttable.getProperty(propr).toString());
                }
                ArrayListHash.add(map);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("poiuy", e.getMessage());
        }

        return ArrayListHash;
    }


    public ArrayList<HashMap<String, String>> delete(String id , String id_details) {
        String FName = "DeleteRK_BranchwDetails";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="ID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(id);

        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="BranchDetailsID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(id_details);

        request.addProperty(propertyInfo);

        /** end send*/
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);

        /** if Double
         MarshalDouble md = new MarshalDouble();
         md.register(envelope);
         */

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);

        ArrayList<HashMap<String, String>> ArrayListHash = new ArrayList<HashMap<String, String>>();
        try {
            // Invole web service
            androidHttpTransport.call(Constants.SOAP_ACTION + FName, envelope);
            // Get the response

            SoapPrimitive resultsString = (SoapPrimitive) envelope.getResponse();

            HashMap<String , String> map = new HashMap<>();
            map.put("id" , resultsString.toString());
            ArrayListHash.add(map);


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("poiuy", e.getMessage());
        }

        return ArrayListHash;
    }


    class getData extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir;
        private List<HashMap<String , String>> slistHM;


        public getData(onFinishedListener onFinishedListener, String ir) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = getData(ir);
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

            try {
                onFinishedListener.onFinished(slistHM);
            }
            catch (Exception  e){
                onFinishedListener.onFailure(e.getMessage());
            }


        }

    }


    class getDataDetaisl extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir;
        private List<HashMap<String , String>> slistHM;


        public getDataDetaisl(onFinishedListener onFinishedListener, String ir) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = getDataDetails(ir);
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

            try {
                onFinishedListener.onFinished(slistHM);
            }
            catch (Exception  e){
                onFinishedListener.onFailure(e.getMessage());
            }


        }

    }


    class delete extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , id_d;
        private List<HashMap<String , String>> slistHM;


        public delete(onFinishedListener onFinishedListener, String ir , String id_d) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.id_d  = id_d;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = delete(ir , id_d);
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

            try {
                onFinishedListener.onFinished(slistHM);
            }
            catch (Exception  e){
                onFinishedListener.onFailure(e.getMessage());
            }


        }

    }
}

