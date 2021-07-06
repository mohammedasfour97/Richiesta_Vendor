package com.richiestavendor.SubSections.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Constants;
import com.richiestavendor.SubSections.Contract;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubSectionsModel implements Contract.SubSections.Model {

    @Override
    public void getSubSections(onFinishedListener onFinishedListener, String id, String user_id) {

        new getData(onFinishedListener , id , user_id).execute();
    }


    public ArrayList<HashMap<String, String>> getData(String id , String id_user) {
        String FName = "SelectRK_Categories";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

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



            String [] prop = new String[] {"RK_Stores", "CategoryNameEN", "CategoryNameAr", "ParentCategory", "CategoryNotes", "Notes", "Status", "Ranking",
                    "ARDescription", "ENDescription", "FRDescription", "ARImage", "ENImage", "FRImage", "ID_Stores", "ID_Branches", "ID_USER",
                    "DateEdite", "DateCreate"
            };
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


        public getData(Contract.SubSections.Model.onFinishedListener onFinishedListener, String ir, String user_id) {
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

