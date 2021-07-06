package com.richiestavendor.Additions.Models;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Additions.Contract;
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

public class AdditionsModel implements Contract.Additions.Model {


    @Override
    public void getColors(onFinishedListener onFinishedListener, String id) {

        new getColors(onFinishedListener , id , "SelectRK_ProductColorByStoreID" , "StoreID").execute();
    }

    @Override
    public void getTags(onFinishedListener onFinishedListener, String id) {

        new getColors(onFinishedListener , id , "Selectsprk_SelectRK_TagByStoreID" , "_StoreId").execute();
    }

    @Override
    public void getSizes(onFinishedListener onFinishedListener, String id) {

        new getColors(onFinishedListener , id , "SelectRK_ProductSizeByStoreID" , "StoreID").execute();
    }

    @Override
    public void getCategories(onFinishedListener onFinishedListener, String id) {

        new getColors(onFinishedListener , id , "Selectsprk_SelectRK_CategoriesByStoreId" , "_StoreId").execute();
    }

    @Override
    public void getAdditions(onFinishedListener onFinishedListener, String id) {

        new getColors(onFinishedListener , id , "SelectRK_AdditionalsByStoreID" , "StoreID").execute();
    }

    @Override
    public void getDeleteColor(onFinishedListener onFinishedListener, String id) {

        new DeleteAddition(onFinishedListener , id , "color").execute();
    }

    @Override
    public void getDeleteCategory(onFinishedListener onFinishedListener, String id) {

        new DeleteAddition(onFinishedListener , id , "cat").execute();
    }

    @Override
    public void getDeleteSize(onFinishedListener onFinishedListener, String id) {

        new DeleteAddition(onFinishedListener , id , "size").execute();
    }

    @Override
    public void getDeleteAddition(onFinishedListener onFinishedListener, String id) {

        new DeleteAddition(onFinishedListener , id , "addition").execute();
    }


    public ArrayList<HashMap<String, String>> getData(String id , String fun , String parm) {
        String FName = fun;
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name=parm;
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



            String [] prop = new String[] {"title", "ColorHex" , "ClrNameAR" , "ClrNameEN" , "ClrNotes" , "CreatedBy" , "CreatedDate" , "ModifiedBy" ,
                    "ModifiedDate" , "SizeNameEN" , "SizeNameAR" , "SizeUnitType" , "SizeNotes", "TagNameAR" , "TagNameEN" , "RK_Products" , "TagNotes" ,
                    "CategoryNameEN" , "CategoryNameAr" , "ParentCategory" , "CategoryNotes" , "Additional" , "AdditionalAr" , "id" , "ID"};
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



    public ArrayList<HashMap<String, String>> DeleteAddition(String id , String m) {

        String FName ;

        switch (m){

            case "color" :
                FName = "DeleteRK_Colors";
                break;

            case "cat" :
                FName = "DeleteRK_Categories";
                break;

            case "size" :
                FName = "DeleteRK_Size";
                break;

            default:
                FName = "DeleteRK_Additionals";
                break;
        }
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setValue(id);
        propertyInfo.setType(String.class);
        propertyInfo.setName("ID");

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


    class DeleteAddition extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , m;
        private List<HashMap<String , String>> slistHM ;


        public DeleteAddition(onFinishedListener onFinishedListener, String ir, String m) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.m = m;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = DeleteAddition(ir , m);
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


    class getColors extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , fun , parm;
        private List<HashMap<String , String>> slistHM;


        public getColors(Contract.Additions.Model.onFinishedListener onFinishedListener, String ir, String fun, String parm) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.fun = fun;
            this.parm = parm;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = getData(ir , fun , parm);
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



