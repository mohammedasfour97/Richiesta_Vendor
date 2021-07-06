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

public class AddBranchModel implements Contract.AddBranches.Model {
    @Override
    public void getAddBranches(onFinishedListener onFinishedListener, String NameAR, String NameEN, String Feild, String RK_StoresContact, String RK_Stores,
                               String RK_branchNotes, String CreatedBy) {

        new AddBranch(onFinishedListener, NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, CreatedBy).execute();

    }

    @Override
    public void getModifyBranches(onFinishedListener onFinishedListener, String id, String NameAR, String NameEN, String Feild,
                                  String RK_StoresContact, String RK_Stores, String RK_branchNotes, String CreatedBy) {

        new AddBranch(onFinishedListener, id , NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, CreatedBy).execute();
    }

    @Override
    public void getAddBranchDetails(onFinishedListener onFinishedListener  , String RK_Branch_ID, String RK_Address, String RK_City,
                                    String RK_Country, String RK_Phone, String RK_Cell,
                                    String RK_Email, String RK_StoreNotes, String Createdby , String longitude , String latidude) {

        new AddBranchDetails(onFinishedListener , RK_Branch_ID,  RK_Address,  RK_City,  RK_Country,  RK_Phone,RK_Cell,  RK_Email,  RK_StoreNotes,
                Createdby ,  longitude , latidude).execute();
    }

    @Override
    public void getCountries(onFinishedListener onFinishedListener) {

        new GetCC(onFinishedListener , "country").execute();

    }

    @Override
    public void getCities(onFinishedListener onFinishedListener) {

        new GetCC(onFinishedListener , "city").execute();
    }


    public ArrayList<HashMap<String, String>> addBranch(String NameAR, String NameEN, String Feild, String RK_StoresContact,
                                                        String RK_Stores, String RK_branchNotes, String CreatedBy) {
        String FName = "InsertRK_Branch";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="NameAR";
        propertyInfo.type=String.class;
        propertyInfo.setValue(NameAR);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="NameEN";
        propertyInfo.type=String.class;
        propertyInfo.setValue(NameEN);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Feild";
        propertyInfo.type=String.class;
        propertyInfo.setValue(Feild);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_StoresContact";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(Integer.parseInt(RK_StoresContact));
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Stores";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(Integer.parseInt(RK_Stores));
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_branchNotes";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_branchNotes);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Createdby";
        propertyInfo.type=String.class;
        propertyInfo.setValue(CreatedBy);
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
                String m = Objecttable.getPropertyInfo(i).toString();
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

    public ArrayList<HashMap<String, String>> ModifyBranch(String id , String NameAR, String NameEN, String Feild, String RK_StoresContact,
                                                        String RK_Stores,
                                                        String RK_branchNotes, String CreatedBy) {
        String FName = "ModifyRK_Branch_Details";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="ID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(id);

        request.addProperty(propertyInfo);
        propertyInfo = new PropertyInfo();
        propertyInfo.name="NameAR";
        propertyInfo.type=String.class;
        propertyInfo.setValue(NameAR);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="NameEN";
        propertyInfo.type=String.class;
        propertyInfo.setValue(NameEN);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Feild";
        propertyInfo.type=String.class;
        propertyInfo.setValue(Feild);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_StoresContact";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(Integer.parseInt(RK_StoresContact));
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Stores";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(Integer.parseInt(RK_Stores));
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_branchNotes";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_branchNotes);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Createdby";
        propertyInfo.type=String.class;
        propertyInfo.setValue(CreatedBy);
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





    public ArrayList<HashMap<String, String>> addBranchDetails(String RK_Branch_ID, String RK_Address, String RK_City, String RK_Country,
                                                               String RK_Phone, String RK_Cell, String RK_Email, String RK_StoreNotes,
                                                               String Createdby , String lan , String lat) {
        String FName = "InsertRK_Branch_Details";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Branch_ID";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(Integer.parseInt(RK_Branch_ID));
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Address";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_Address);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_City";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_City);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Country";
        propertyInfo.type=Double.class;
        propertyInfo.setValue(Integer.parseInt(RK_Country));
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Phone";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_Phone);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Cell";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_Cell);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_Email";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_Email);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="RK_StoreNotes";
        propertyInfo.type=String.class;
        propertyInfo.setValue(RK_StoreNotes);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Createdby";
        propertyInfo.type=String.class;
        propertyInfo.setValue(Createdby);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Longitude";
        propertyInfo.type=String.class;
        propertyInfo.setValue(lan);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Latitude";
        propertyInfo.type=String.class;
        propertyInfo.setValue(lat);
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

        /*    SoapPrimitive resultsString = (SoapPrimitive) envelope.getResponse();
            String response = resultsString.toString();

         */
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            SoapObject Object1 = (SoapObject) resultsString.getProperty(1);
            SoapObject tables = (SoapObject) Object1.getProperty(0);


            String [] prop = new String[] {"id"};
            SoapObject Objecttable;
            HashMap<String, String> map;
            for (int i = 0; i < tables.getPropertyCount(); i++) {
                Objecttable = (SoapObject) tables.getProperty(i);
                map = new HashMap<String, String>();
                String m = Objecttable.getPropertyInfo(i).toString();
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



    public ArrayList<HashMap<String, String>> getCC(String c) {

        String FName;
        if (c.equals("country")) FName = "SelectCountry";
        else FName = "SelectCity";
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

        /*    SoapPrimitive resultsString = (SoapPrimitive) envelope.getResponse();
            String response = resultsString.toString();

         */
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            SoapObject Object1 = (SoapObject) resultsString.getProperty(1);
            SoapObject tables = (SoapObject) Object1.getProperty(0);


            String [] prop = new String[] {"ID" , "ARCountry" , "ENCountry" , "CityAR" , "CityEN"};

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


    class AddBranch extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, atedBy;
        private List<HashMap<String , String>> slistHM;
        private String id;


        public AddBranch(Contract.AddBranches.Model.onFinishedListener onFinishedListener, String nameAR, String nameEN, String feild,
                         String RK_StoresContact,
                       String RK_Stores, String RK_branchNotes, String atedBy) {
            this.onFinishedListener = onFinishedListener;
            NameAR = nameAR;
            NameEN = nameEN;
            Feild = feild;
            this.RK_StoresContact = RK_StoresContact;
            this.RK_Stores = RK_Stores;
            this.RK_branchNotes = RK_branchNotes;
            this.atedBy = atedBy;
        }

        public AddBranch(Contract.AddBranches.Model.onFinishedListener onFinishedListener, String id , String nameAR, String nameEN,
                         String feild, String RK_StoresContact,
                         String RK_Stores, String RK_branchNotes, String atedBy) {
            this.onFinishedListener = onFinishedListener;
            NameAR = nameAR;
            NameEN = nameEN;
            Feild = feild;
            this.RK_StoresContact = RK_StoresContact;
            this.RK_Stores = RK_Stores;
            this.RK_branchNotes = RK_branchNotes;
            this.atedBy = atedBy;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            if (id!=null) slistHM = ModifyBranch(id , NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, atedBy);
            else slistHM = addBranch(NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, atedBy);
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


    class AddBranchDetails extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String RK_Branch_ID,  RK_Address,  RK_City,  RK_Country,  RK_Phone,RK_Cell,  RK_Email,  RK_StoreNotes,  Createdby ,  lan , lat;
        private List<HashMap<String , String>> slistHM;


        public AddBranchDetails(Contract.AddBranches.Model.onFinishedListener onFinishedListener, String RK_Branch_ID, String RK_Address,
                                String RK_City, String RK_Country, String RK_Phone, String RK_Cell, String RK_Email, String RK_StoreNotes,
                                String createdby, String lan, String lat) {
            this.onFinishedListener = onFinishedListener;
            this.RK_Branch_ID = RK_Branch_ID;
            this.RK_Address = RK_Address;
            this.RK_City = RK_City;
            this.RK_Country = RK_Country;
            this.RK_Phone = RK_Phone;
            this.RK_Cell = RK_Cell;
            this.RK_Email = RK_Email;
            this.RK_StoreNotes = RK_StoreNotes;
            Createdby = createdby;
            this.lan = lan;
            this.lat = lat;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = addBranchDetails(RK_Branch_ID,  RK_Address,  RK_City,  RK_Country,  RK_Phone,RK_Cell,  RK_Email,  RK_StoreNotes,  Createdby ,
                    lan , lat);
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


    class GetCC extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String c;
        private List<HashMap<String , String>> slistHM;


        public GetCC(Contract.AddBranches.Model.onFinishedListener onFinishedListener, String c) {
            this.onFinishedListener = onFinishedListener;
            this.c = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {

            slistHM = getCC(c);
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

