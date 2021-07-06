package com.richiestavendor.Auth.Models;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Auth.Contract;
import com.richiestavendor.BaseModel;
import com.richiestavendor.Constants;
import com.richiestavendor.Store;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginModel extends BaseModel implements Contract.Login.Model {

    @Override
    public void getLogin(onFinishedListener onFinishedListener, String number , String password) {

        new Login(onFinishedListener , number , password).execute();
    }

    public ArrayList<HashMap<String, String>> login(String number, String password) {
        String FName = "RK_StoreLogin";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Phone";
        propertyInfo.type=String.class;
        propertyInfo.setValue(number);
        request.addProperty(propertyInfo);

        propertyInfo = new PropertyInfo();
        propertyInfo.name="Password";
        propertyInfo.type=String.class;
        propertyInfo.setValue(password);
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


            String [] prop = new String[] {"ID" , "Phone"  , "RK_Branch" , "isMainBranch" , "RK_Stores" , "IsStoreAdmin", "RKStoreNameAr",
            "RKStoreNameEN", "RKStoreLogo", "RK_Stores1", "IsStoreAdmin"};
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

    private void getUpdateDataInDb(HashMap<String,String> r){

        Store store = new Store(r.get("ID"), r.get("Phone"), r.get("RK_Branch"), r.get("isMainBranch"), r.get("RK_Stores"),
                r.get("RKStoreNameAr"), r.get("RKStoreNameEN"), r.get("RKStoreLogo"), r.get("RK_Stores1"),
                r.get("IsStoreAdmin"));

        new SelectUsersAsyncTask(store).execute();
    }


    class Login extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private List<HashMap<String , String>> slistHM;
        private String phone , password;


        public Login(Contract.Login.Model.onFinishedListener onFinishedListener, String phone, String password) {
            this.onFinishedListener = onFinishedListener;
            this.phone = phone;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = login(phone , password);
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

            try {

                getUpdateDataInDb(slistHM.get(0));

                onFinishedListener.onFinished(slistHM);
            }
            catch (Exception  e){
                onFinishedListener.onFailure(e.getMessage());
            }


        }

    }

    private class InsertUserAsyncTask extends AsyncTask<Store, Void, Void> {


        @Override
        protected Void doInBackground(Store... stores) {
            storeDao.insert(stores[0]);
            return null;
        }
    }
    private class UpdateUserAsyncTask extends AsyncTask<Store, Void, Void> {


        @Override
        protected Void doInBackground(Store... stores) {
            storeDao.update(stores[0]);
            return null;
        }
    }

    private class SelectUsersAsyncTask extends AsyncTask<Void, Void, List<Store>> {

        private Store store;

        public SelectUsersAsyncTask(Store store) {
            this.store = store;
        }

        @Override
        protected List<Store> doInBackground(Void... voids) {
            return storeDao.getStoreDetails();
        }

        @Override
        protected void onPostExecute(List<Store> stores) {

            if (stores.isEmpty())
                new InsertUserAsyncTask().execute(store);
            else
                new UpdateUserAsyncTask().execute(store);


        }
    }
}
