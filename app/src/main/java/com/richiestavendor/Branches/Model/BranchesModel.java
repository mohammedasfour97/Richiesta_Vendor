package com.richiestavendor.Branches.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Branches.Contract;
import com.richiestavendor.Constants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BranchesModel implements Contract.Branches.Model {


    @Override
    public void getBranches(onFinishedListener onFinishedListener, String id, String user_id) {

        new getData(onFinishedListener , id , user_id).execute();
    }


    public ArrayList<HashMap<String, String>> getData(String id , String user_id) {
        String FName = "GetALLBranches";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="ID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(id);

        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="ID_User";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(user_id);

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



            String [] prop = new String[] {"ID", "ID_Stores", "CPName", "ARName", "ENName", "ARAddress", "ENAddress", "Longitude", "Latitude",
                    "ID_Country", "ID_City", "ID_Region", "Tel", "ad_state", "us_state", "Ranking", "ID_USER", "DateEdite", "DateCreate"};
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


    class getData extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , user_id;
        private List<HashMap<String , String>> slistHM;


        public getData(onFinishedListener onFinishedListener, String ir, String user_id) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.user_id = user_id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = getData(ir , user_id);
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

