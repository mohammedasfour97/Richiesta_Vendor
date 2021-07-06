package com.richiestavendor.Additions.Models;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Additions.Contract;
import com.richiestavendor.Constants;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.ModelClasses.SoupProperty;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddAdditionModel implements Contract.Add_Addition.Model {

    @Override
    public void getAddColor(onFinishedListener onFinishedListener, String id, Addition addition) {

        new AddAddition(onFinishedListener , id , "color" , addition).execute();
    }

    @Override
    public void getAddCategory(onFinishedListener onFinishedListener, String id, Addition addition) {

        new AddAddition(onFinishedListener , id , "cat" , addition).execute();
    }

    @Override
    public void getAddSize(onFinishedListener onFinishedListener, String id, Addition addition) {

        new AddAddition(onFinishedListener , id , "size" , addition).execute();
    }

    @Override
    public void getAddAddition(onFinishedListener onFinishedListener, String id, Addition addition) {

        new AddAddition(onFinishedListener , id , "addition" , addition).execute();
    }

    public ArrayList<HashMap<String, String>> addAddition(String id , Addition addition , String m) {

        String FName ;

        List<SoupProperty> soupPropertyList = new ArrayList<>();

        switch (m){

            case "color" :
                FName = "InsertRK_Colors";
                soupPropertyList.add(new SoupProperty("ColorHex" , addition.getColorHex() , String.class ));
                soupPropertyList.add(new SoupProperty("ClrNameAR" , addition.getClrNameAR() , String.class ));
                soupPropertyList.add(new SoupProperty("ClrNameEN" , addition.getClrNameEN() , String.class ));
                soupPropertyList.add(new SoupProperty("ClrNotes" , addition.getClrNotes() , String.class ));
                soupPropertyList.add(new SoupProperty("Createdby" , addition.getCreatedBy() , String.class ));
                soupPropertyList.add(new SoupProperty("RK_Stores" , id , Integer.class ));
                break;

            case "cat" :
                FName = "InsertRK_Categories";
                soupPropertyList.add(new SoupProperty("CategoryNameAr" , addition.getCategoryNameAr() , String.class ));
                soupPropertyList.add(new SoupProperty("CategoryNameEN" , addition.getCategoryNameEN() , String.class ));
                soupPropertyList.add(new SoupProperty("ParentCategory" , addition.getParentCategory() , String.class ));
                soupPropertyList.add(new SoupProperty("CategoryNotes" , addition.getCategoryNotes() , String.class ));
                soupPropertyList.add(new SoupProperty("Createdby" , addition.getCreatedBy() , String.class ));
                soupPropertyList.add(new SoupProperty("RK_Stores" , id , Integer.class ));
                break;

            case "size" :
                FName = "InsertRK_Size";
                soupPropertyList.add(new SoupProperty("SizeNameEN" , addition.getSizeNameEN() , String.class ));
                soupPropertyList.add(new SoupProperty("SizeNameAR" , addition.getSizeNameAR() , String.class ));
                soupPropertyList.add(new SoupProperty("SizeUnitType" , addition.getSizeUnitType() , String.class ));
                soupPropertyList.add(new SoupProperty("SizeNotes" , addition.getSizeNotes() , String.class ));
                soupPropertyList.add(new SoupProperty("Createdby" , addition.getCreatedBy() , String.class ));
                soupPropertyList.add(new SoupProperty("RK_Stores" , id , Integer.class ));
                break;

            default:
                FName = "InsertRK_Additionals";
                soupPropertyList.add(new SoupProperty("Additional" , addition.getAdditional() , String.class ));
                soupPropertyList.add(new SoupProperty("AdditionalAr" , addition.getAdditionalAr() , String.class ));
                soupPropertyList.add(new SoupProperty("Createdby" , addition.getCreatedBy() , String.class ));
                soupPropertyList.add(new SoupProperty("RK_Stores" , id , Integer.class ));
                break;
        }
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        for (SoupProperty soupProperty : soupPropertyList){

            propertyInfo = new PropertyInfo();
            propertyInfo.name=soupProperty.getName();
            propertyInfo.type=soupProperty.getType();
            if (propertyInfo.type == String.class)
                propertyInfo.setValue(soupProperty.getString_value());
            else propertyInfo.setValue(Integer.parseInt(soupProperty.getString_value()));

            request.addProperty(propertyInfo);

        }

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

          /*  SoapPrimitive resultsString = (SoapPrimitive) envelope.getResponse();
            String response = resultsString.toString();

           */
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            SoapObject Object1 = (SoapObject) resultsString.getProperty(1);
            SoapObject tables = (SoapObject) Object1.getProperty(0);


            String [] prop = new String[] {"ID"};
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


    class AddAddition extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , m;
        private Addition addition;
        private List<HashMap<String , String>> slistHM ;


        public AddAddition(Contract.Add_Addition.Model.onFinishedListener onFinishedListener, String ir, String m , Addition addition) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.m = m;
            this.addition = addition;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = addAddition(ir , addition , m);
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
