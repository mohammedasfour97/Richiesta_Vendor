package com.richiestavendor.Products.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Constants;
import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.ModelClasses.SoupProperty;
import com.richiestavendor.Products.Contract;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsModel implements Contract.Products.Model {


    @Override
    public void getProducts(onFinishedListener onFinishedListener, String id, String user_id) {

        new getData(onFinishedListener , id , user_id).execute();
    }

    @Override
    public void getProductsAdd(onFinishedListener onFinishedListener, String id, String user_id) {

        new GetAdd(onFinishedListener , id , user_id).execute();
    }

    @Override
    public void getUploadImg(onFinishedListener onFinishedListener, byte[] bytes ,String img_name) {

        new UploadMedia(onFinishedListener  , bytes , img_name).execute();
    }

    @Override
    public void getAddProducts(onFinishedListener onFinishedListener, Product product) {

        new getData2(onFinishedListener , product).execute();
    }

    @Override
    public void getModifyProducts(onFinishedListener onFinishedListener, Product product){

        new ModifyProduct(onFinishedListener , product).execute();
    }

    @Override
    public void getDeleteProducts(onFinishedListener onFinishedListener, String id) {

        new delete(onFinishedListener , id).execute();
    }


    private ArrayList<HashMap<String, String>> delete(String id ) {
        String FName = "DeleteRK_Products";
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


    private ArrayList<HashMap<String, String>> getData(String id) {
        String FName = "Selectsprk_SelectRK_ProductsByBranchId";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="BranchID";
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

            String [] prop = new String[] {"id" , "RKPrdNameEN", "RKPrdNameAR", "RKPrdDescrEN", "RKPrdDescrAR", "RKBranches_Id", "ProductNumber", "ProductUPC",
                    "PurchaseUnitPrice", "SalesUnitPrice", "ProductPic", "RK_Categories", "RK_Size", "RK_Colors", "IsAvailable", "RK_Notes", "CreatedBy",
                    "FRUse", "ARImage", "ENImage", "FRImage", "Code", "Status", "sales", "Views", "Searches", "Comments", "Ratings", "AvgRating", "Notes",
                    "CreatedDate", "ModifiedBy", "ModifiedDate" , "RK_ProductAdditionals" , "RK_ProductColor" , "RK_ProductSize" ,
                    "MeasurmentUnit", "MeasurmentUnitAr", "ColorPrice" , "AdditionalPrice", "SizePrice"
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


    private ArrayList<HashMap<String, String>> getAdd(String id) {
        String FName = "Selectvw_Apr2020RK_ProdClrSizAdditionViewByProdId";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo propertyInfo;

        propertyInfo = new PropertyInfo();
        propertyInfo.name="_ProductId";
        propertyInfo.type=Integer.class;
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
            Log.d("om_el5ara", resultsString.toString());
            SoapObject Object1 = (SoapObject) resultsString.getProperty(1);
            SoapObject tables = (SoapObject) Object1.getProperty(0);

            String [] prop = new String[] {"SizeID" , "AdditionalID" , "ColorsID" };
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


    private ArrayList<HashMap<String, String>> getData(Product product) {
        String FName = "InsertRK_Products";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        List<SoupProperty> soupPropertyList = new ArrayList<>();

        soupPropertyList.add(new SoupProperty("RKPrdNameEN" , product.getRKPrdNameEN() , String.class ));
        soupPropertyList.add(new SoupProperty("RKPrdNameAR" , product.getRKPrdNameAR() , String.class ));
        soupPropertyList.add(new SoupProperty("RKPrdDescrEN" , product.getRKPrdDescrEN() , String.class ));
        soupPropertyList.add(new SoupProperty("RKPrdDescrAR" , product.getRKPrdDescrAR() , String.class ));
        soupPropertyList.add(new SoupProperty("RKBranches_Id" , product.getRKBranches_Id() , Integer.class ));
        soupPropertyList.add(new SoupProperty("ProductNumber" , product.getProductNumber() , String.class ));
        soupPropertyList.add(new SoupProperty("ProductUPC" , product.getProductUPC() , String.class ));
        soupPropertyList.add(new SoupProperty("PurchaseUnitPrice" , product.getPurchaseUnitPrice() , Float.class ));
        soupPropertyList.add(new SoupProperty("SalesUnitPrice" , product.getSalesUnitPrice() , Float.class ));
        soupPropertyList.add(new SoupProperty("ProductPic" , product.getProductPic() , String.class ));
        soupPropertyList.add(new SoupProperty("RK_Categories" , product.getRK_Categories() , Integer.class ));
        soupPropertyList.add(new SoupProperty("RK_ProductColor" , product.getRK_Colors() , String.class ));
        soupPropertyList.add(new SoupProperty("RK_ProductSize" , product.getRK_Size() , String.class ));
        soupPropertyList.add(new SoupProperty("RK_ProductAdditionals" , product.getRK_Additins() , String.class ));
        soupPropertyList.add(new SoupProperty("Createdby " , product.getCreatedBy () , String.class ));
        soupPropertyList.add(new SoupProperty("MeasurmentUnit " , product.getMeasurmentUnit () , String.class ));
        soupPropertyList.add(new SoupProperty("MeasurmentUnitAr " , product.getMeasurmentUnitAr () , String.class ));
        soupPropertyList.add(new SoupProperty("ColorPrice " , product.getColorPrice () , String.class ));
        soupPropertyList.add(new SoupProperty("AdditionalPrice " , product.getAdditionalPrice () , String.class ));
        soupPropertyList.add(new SoupProperty("SizePrice " , product.getSizePrice () , String.class ));
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

        propertyInfo = new PropertyInfo();
        propertyInfo.name="IsAvailable";
        propertyInfo.type=boolean.class;
        propertyInfo.setValue(product.getIsAvailable());
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


    private ArrayList<HashMap<String, String>> modifyProduct(Product product) {
        String FName = "ModifyRK_Products";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        List<SoupProperty> soupPropertyList = new ArrayList<>();

        soupPropertyList.add(new SoupProperty("RKPrdNameEN" , product.getRKPrdNameEN() , String.class ));
        soupPropertyList.add(new SoupProperty("ID" , product.getRKBranches_Id() , String.class ));
        soupPropertyList.add(new SoupProperty("RKPrdNameAR" , product.getRKPrdNameAR() , String.class ));
        soupPropertyList.add(new SoupProperty("RKPrdDescrEN" , product.getRKPrdDescrEN() , String.class ));
        soupPropertyList.add(new SoupProperty("RKPrdDescrAR" , product.getRKPrdDescrAR() , String.class ));
        soupPropertyList.add(new SoupProperty("RKBranches_Id" , product.getRKBranches_Id() , Integer.class ));
        soupPropertyList.add(new SoupProperty("ProductNumber" , product.getProductNumber() , String.class ));
        soupPropertyList.add(new SoupProperty("ProductUPC" , product.getProductUPC() , String.class ));
        soupPropertyList.add(new SoupProperty("PurchaseUnitPrice" , product.getPurchaseUnitPrice() , Float.class ));
        soupPropertyList.add(new SoupProperty("SalesUnitPrice" , product.getSalesUnitPrice() , Float.class ));
        soupPropertyList.add(new SoupProperty("ProductPic" , product.getProductPic() , String.class ));
        soupPropertyList.add(new SoupProperty("RK_Categories" , product.getRK_Categories() , Integer.class ));
        soupPropertyList.add(new SoupProperty("RK_ProductColor" , product.getRK_Colors() , String.class ));
        soupPropertyList.add(new SoupProperty("RK_ProductSize" , product.getRK_Size() , String.class ));
        soupPropertyList.add(new SoupProperty("RK_ProductAdditionals" , product.getRK_Additins() , String.class ));
        soupPropertyList.add(new SoupProperty("Createdby " , product.getCreatedBy () , String.class ));

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

        propertyInfo = new PropertyInfo();
        propertyInfo.name="IsAvailable";
        propertyInfo.type=boolean.class;
        propertyInfo.setValue(product.getIsAvailable());
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


    public ArrayList<HashMap<String, String>> upload_media(byte[] bitmapdata, String img_name) {
        String FName = "UploadFile";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, FName);

        PropertyInfo pid;

        pid = new PropertyInfo();
        pid.name="fle";
        pid.type=Byte[].class;
        pid.setValue(bitmapdata);

        request.addProperty(pid);

        pid = new PropertyInfo();
        pid.name="FileName";
        pid.type=String.class;
        pid.setValue(img_name);

        request.addProperty(pid);


        /** end send*/
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        new MarshalBase64().register(envelope); //serialization
        envelope.encodingStyle = SoapEnvelope.ENC;



        // Set output SOAP object
        envelope.setOutputSoapObject(request);

        /** if Double
         MarshalDouble md = new MarshalDouble();
         md.register(envelope);
         */

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);

        ArrayList<HashMap<String , String>> hashMaps = new ArrayList<>();
        try {
            // Invole web service
            androidHttpTransport.call(Constants.SOAP_ACTION + FName, envelope);
            // Get the response

            SoapPrimitive resultsString = (SoapPrimitive) envelope.getResponse();

            HashMap<String , String> map = new HashMap<>();
            map.put("done" , resultsString.toString());
            hashMaps.add(map);


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("poiuy", e.getMessage());
        }

        return hashMaps;
    }




    class getData extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , user_id;
        private List<HashMap<String , String>> slistHM ;


        public getData(Contract.Products.Model.onFinishedListener onFinishedListener, String ir, String user_id) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.user_id = user_id;
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


    class GetAdd extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private String ir , user_id;
        private List<HashMap<String , String>> slistHM ;


        public GetAdd(Contract.Products.Model.onFinishedListener onFinishedListener, String ir, String user_id) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
            this.user_id = user_id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = getAdd(ir);
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


    class getData2 extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private Product product;
        private List<HashMap<String , String>> slistHM;

        public getData2(onFinishedListener onFinishedListener , Product product) {
            this.onFinishedListener = onFinishedListener;
            this.product = product;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = getData(product);
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


    class ModifyProduct extends AsyncTask<String, String, String> {

        private onFinishedListener onFinishedListener;
        private Product product;
        private List<HashMap<String , String>> slistHM;

        public ModifyProduct(onFinishedListener onFinishedListener , Product product) {
            this.onFinishedListener = onFinishedListener;
            this.product = product;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = modifyProduct(product);
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
        private String ir;
        private List<HashMap<String , String>> slistHM;


        public delete(onFinishedListener onFinishedListener, String ir ) {
            this.onFinishedListener = onFinishedListener;
            this.ir = ir;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = delete(ir);
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

    class UploadMedia extends AsyncTask<String, String, String> {

        byte[] bitmapdata;
        String img_name;
        private onFinishedListener onFinishedListener;
        private List<HashMap<String , String>> slistHM;

        public UploadMedia(onFinishedListener onFinishedListener , byte[] bitmapdata , String img_name) {
            this.bitmapdata = bitmapdata;
            this.img_name = img_name;
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            slistHM = upload_media(bitmapdata , img_name);
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
                Log.d("errrrrorr", e.getMessage());
            }

        }

    }

}

