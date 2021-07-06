package com.richiestavendor.Products.View;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.richiestavendor.Additions.Presenters.AdditionsPresenter;
import com.richiestavendor.BaseActivity;
import com.richiestavendor.Constants;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.Products.Contract;
import com.richiestavendor.Products.Presenter.ProductsPresenter;
import com.richiestavendor.R;
import com.richiestavendor.TinyDB;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddProductActivity extends BaseActivity implements Contract.Products.View , com.richiestavendor.Additions.Contract.Additions.View {

    private EditText RKPrdNameEN, RKPrdNameAR, RKPrdDescrEN, RKPrdDescrAR, ProductNumber, ProductUPC, PurchaseUnitPrice,
            SalesUnitPrice, RK_Notes , measurement_ar , measurement_en;
    private Spinner RKBranches_Id , RK_Categories ;
    private RecyclerView RK_Size, RK_Colors , RK_Additional;
    private ImageView ProductPic;
    private RadioGroup IsAvailable;
    private RadioButton available , not_available;
    private Button save;
    private String sRKPrdNameEN, sRKPrdNameAR, sRKPrdDescrEN, sRKPrdDescrAR, sRKBranches_Id, sProductNumber, sProductUPC,
            sPurchaseUnitPrice, sSalesUnitPrice, sProductPic, sRK_Categories, sRK_Size, sRK_Colors, sRK_Add ,sIsAvailable, sRK_Notes ,
            s_meausrement_ar, s_measureent_en ,sCreatedby , s_size_price , s_color_price , s_addition_price ;
    private boolean is_available;
    private ProductsPresenter productsPresenter;
    private Date currentdate;
    private DateFormat dateformat;
    private AdditionsPresenter additionsPresenter;
    private ArrayAdapter spinnerArrayAdapter;
    private List<Addition> add_ids;
    private List<Addition> sizes_ids;
    private List<Addition> colors_ids;
    private List<String> selected_color_list , selected_size_list , selected_additional_list;
    private Product product;
    private TinyDB tinyDB;
    private final int PICK_IMAGE_REQUEST = 1;
    private byte[] img_bytes ;
    private AdditionsAdapter additional_adapter , colors_adapter , size_adapter;
    private Map<String,String> colors_price , sizes_price , additions_price;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initUI();


        setListers();

        initRecyclerViews();



        tinyDB = new TinyDB(this);


        sRK_Colors = "";  sRK_Add = "";     sRK_Size = ""; s_color_price = ""; s_size_price = ""; s_addition_price = "";

        colors_price = new HashMap<>();
        sizes_price = new HashMap<>();
        additions_price = new HashMap<>();

        productsPresenter = new ProductsPresenter(this);
        additionsPresenter = new AdditionsPresenter(this);

        if (getIntent().getStringExtra("edit").equals("yes")) {
            product = (Product) getIntent().getSerializableExtra("p");
            setData();
        }



        fillSpiners();

    }


    private void initUI(){

        RKPrdNameEN = findViewById(R.id.RKPrdNameEN);
        RKPrdNameAR = findViewById(R.id.RKPrdNameAR);
        RKPrdDescrEN = findViewById(R.id.RKPrdDescrEN);
        RKPrdDescrAR = findViewById(R.id.RKPrdDescrAR);
        RKBranches_Id = findViewById(R.id.RKBranches_Id);
        ProductNumber = findViewById(R.id.ProductNumber);
        ProductUPC = findViewById(R.id.ProductUPC);
        PurchaseUnitPrice = findViewById(R.id.PurchaseUnitPrice);
        SalesUnitPrice = findViewById(R.id.SalesUnitPrice);
        ProductPic = findViewById(R.id.ProductPic);
        RK_Categories = findViewById(R.id.RK_Categories);
        RK_Size = findViewById(R.id.RK_Size);
        RK_Colors = findViewById(R.id.RK_Colors);
        RK_Additional = findViewById(R.id.RK_Additional);
        IsAvailable = findViewById(R.id.IsAvailable);
        RK_Notes = findViewById(R.id.RK_Notes);
        measurement_ar = findViewById(R.id.mesurment_unit_ar);
        measurement_en = findViewById(R.id.mesurment_unit_en);
        save = findViewById(R.id.save);
    }


    private void setListers(){

        ///// Spinners ///////

        RKBranches_Id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Branch branch = (Branch) (parent.getItemAtPosition(position));
                sRKBranches_Id = branch.getRK_Branch_ID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RK_Categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Addition value = (Addition) (parent.getItemAtPosition(position));
                sRK_Categories = value.getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ////// Radio group ////////

        is_available = true;

        IsAvailable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                is_available = checkedId == R.id.available;
            }
        });


        ProductPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Constants.requestStoragePermission(AddProductActivity.this))
                    chooseImg();
            }
        });

        //////// Save button ////////

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowProgress();
                productsPresenter.requestUploadImg(img_bytes , sProductPic);

            }
        });

    }


    private void initRecyclerViews(){

        selected_additional_list = new ArrayList<>();
        selected_color_list = new ArrayList<>();
        selected_size_list = new ArrayList<>();

        add_ids = new ArrayList<>();
        colors_ids = new ArrayList<>();
        sizes_ids = new ArrayList<>();

        RecyclerView.LayoutManager additional_layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager colors_layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager sizes_layoutManager = new LinearLayoutManager(this);

        additional_adapter = new AdditionsAdapter(this , add_ids);
        colors_adapter = new AdditionsAdapter(this , colors_ids);
        size_adapter = new AdditionsAdapter(this , sizes_ids);


        RK_Additional.setLayoutManager(additional_layoutManager);
        RK_Colors.setLayoutManager(colors_layoutManager);
        RK_Size.setLayoutManager(sizes_layoutManager);

        RK_Additional.setAdapter(additional_adapter);
        RK_Colors.setAdapter(colors_adapter);
        RK_Size.setAdapter(size_adapter);

        RK_Additional.setNestedScrollingEnabled(false);
        RK_Colors.setNestedScrollingEnabled(false);
        RK_Size.setNestedScrollingEnabled(false);
    }


    private void chooseImg(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ProductPic.setImageBitmap(bitmap);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                img_bytes = stream.toByteArray();
                sProductPic = System.currentTimeMillis() + (queryName(filePath)).replace(" " , "_");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from Uri

    private String queryName(Uri filePath) {
        Cursor returnCursor =
                getContentResolver().query(filePath, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }



    private void fillSpiners(){

        ShowProgress();

        additionsPresenter.requestColors(tinyDB.getString("id"));
        additionsPresenter.requestCategories(tinyDB.getString("id"));
        additionsPresenter.requestSizes(tinyDB.getString("id"));
        additionsPresenter.requestAdditions(tinyDB.getString("id"));
        productsPresenter.requestBranches(tinyDB.getString("id"));
    }


    private boolean getTexts(){

        boolean b = true;

        if (TextUtils.isEmpty(RKPrdNameEN.getText())){

            RKPrdNameEN.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.english_name));
            b = false;
        }

        else if (TextUtils.isEmpty(RKPrdNameAR.getText())){

            RKPrdNameAR.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.arabic_name));
            b = false;
        }

        else if (TextUtils.isEmpty(RKPrdDescrEN.getText())){

            sRKPrdDescrEN = RKPrdDescrEN.getText().toString();
            RKPrdDescrEN.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.en_details));
            b = false;
        }

        else if (TextUtils.isEmpty(RKPrdDescrAR.getText())){

            RKPrdDescrAR.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.arabic_details));
            b = false;
        }

        else if (TextUtils.isEmpty(ProductNumber.getText())){

            ProductNumber.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.product_num));
            b = false;
        }

        else if (TextUtils.isEmpty(ProductNumber.getText())){

            ProductNumber.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.product_num));
            b = false;
        }

        else if (TextUtils.isEmpty(ProductUPC.getText())){

            ProductUPC.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.product_upc));
            b = false;
        }

        else if (TextUtils.isEmpty(PurchaseUnitPrice.getText())){

            PurchaseUnitPrice.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.purchase_unit_price));
            b = false;
        }

        else if (TextUtils.isEmpty(SalesUnitPrice.getText())){

            SalesUnitPrice.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.sales_unit_price));
            b = false;
        }

        else if (TextUtils.isEmpty(RK_Notes.getText())){

            RK_Notes.setError(getResources().getString(R.string.enter) + getResources().getString(R.string.notes));
            b = false;
        }

        else if (TextUtils.isEmpty(sRK_Categories)){

            Toast.makeText(this, getResources().getString(R.string.no_cat), Toast.LENGTH_SHORT).show();
            b = false;
        }

        else if (TextUtils.isEmpty(sRKBranches_Id)){

            Toast.makeText(this, getResources().getString(R.string.no_branches), Toast.LENGTH_SHORT).show();
            b = false;
        }

        else if (TextUtils.isEmpty(measurement_ar.getText())){

            Toast.makeText(this, getResources().getString(R.string.enter_measurement_unit), Toast.LENGTH_SHORT).show();
            b = false;
        }

        else if (TextUtils.isEmpty(measurement_en.getText())){

            Toast.makeText(this, getResources().getString(R.string.enter_measurement_unit), Toast.LENGTH_SHORT).show();
            b = false;
        }

        findViewById(R.id.root_view).setFocusable(false);
        findViewById(R.id.root_view).clearFocus();

        return b;
    }



    private void setData(){

        RKPrdNameEN.setText(product.getRKPrdNameEN());
        RKPrdNameAR.setText(product.getRKPrdNameAR());
        RKPrdDescrEN.setText(product.getRKPrdDescrEN());
        RKPrdDescrAR.setText(product.getRKPrdDescrAR());
        ProductUPC.setText(product.getProductUPC());
        PurchaseUnitPrice.setText(product.getPurchaseUnitPrice());
        SalesUnitPrice.setText(product.getSalesUnitPrice());
        ProductNumber.setText(product.getProductNumber());
        RK_Notes.setText(product.getRK_Notes());

        Glide.with(this).load(Constants.IMAGE_URL + product.getProductPic().substring(9)).into(ProductPic);

        RK_Categories.setSelection(getIndex(RK_Categories , product.getRK_Categories()));
        RKBranches_Id.setSelection(getIndex(RKBranches_Id , product.getRKBranches_Id()));

     /*   String id = "";
        for (int a=0 ; a<product.getRK_Size().length() ; a++){

            if (String.valueOf(product.getRK_Size().charAt(a)).equals(",")){

                sizes_ids.add(id);
                id = "";
            }
            else {

                id += String.valueOf(product.getRK_Size().charAt(a));
            }

        }

        for (int a=0 ; a<product.getRK_Additins().length() ; a++){

            if (String.valueOf(product.getRK_Additins().charAt(a)).equals(",")){

                add_ids.add(id);
                id = "";
            }
            else {

                id += String.valueOf(product.getRK_Additins().charAt(a));
            }

        }

        for (int a=0 ; a<product.getRK_Colors().length() ; a++){

            if (String.valueOf(product.getRK_Colors().charAt(a)).equals(",")){

                colors_ids.add(id);
                id = "";
            }
            else {

                id += String.valueOf(product.getRK_Colors().charAt(a));
            }

        }

      */



    }


    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (((Addition)spinner.getItemAtPosition(i)).getId().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

/*
    private void addComboBoxes(LinearLayout layout , List<Addition> additions , final List<String> ids){

        AppCompatCheckBox compatCheckBox;
        EditText editText;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 5, 10, 5);

        for (final Addition addition : additions){

            compatCheckBox = new AppCompatCheckBox(this);
            compatCheckBox.setLayoutParams(params);
            compatCheckBox.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

            if (addition.getAdditionalAr() != null)compatCheckBox.setText(addition.getAdditionalAr());
            else if (addition.getClrNameAR() != null)compatCheckBox.setText(addition.getClrNameAR());
            else compatCheckBox.setText(addition.getSizeNameAR());

            layout.addView(compatCheckBox);

            if ((addition.getId() != null &&ids.contains(addition.getId())) ||
                    (addition.getID() != null &&ids.contains(addition.getID()))){

                compatCheckBox.setChecked(true);
            }


            compatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    String id ;

                    if (addition.getID()!=null) id = addition.getID();
                    else id = addition.getId();

                    if (isChecked) {

                        if (addition.getID() != null) {

                            if (addition.getAdditionalAr() != null) add_ids.add(id);
                            else if (addition.getClrNameAR() != null) colors_ids.add(id);
                            else sizes_ids.add(id);
                        }
                        if (addition.getId() != null) {

                            if (addition.getAdditionalAr() != null) add_ids.add(id);
                            else if (addition.getClrNameAR() != null) colors_ids.add(id);
                            else sizes_ids.add(id);
                        }

                    }
                    else {

                        if (addition.getID() != null) {

                            if (addition.getAdditionalAr() != null) add_ids.remove(id);
                            else if (addition.getClrNameAR() != null) colors_ids.remove(id);
                            else sizes_ids.remove(id);
                        }
                        if (addition.getId() != null) {

                            if (addition.getAdditionalAr() != null) add_ids.remove(id);
                            else if (addition.getClrNameAR() != null) colors_ids.remove(id);
                            else sizes_ids.remove(id);
                        }
                    }
                }
            });
        }
    }

 */

    private void addProduct(){

        if (getTexts()){

            sRKPrdNameEN = RKPrdNameEN.getText().toString();
            sRKPrdNameAR = RKPrdNameAR.getText().toString();
            sRKPrdDescrAR = RKPrdDescrAR.getText().toString();
            sRKPrdDescrEN = RKPrdDescrEN.getText().toString();
            sProductNumber = ProductNumber.getText().toString();
            sProductUPC = ProductUPC.getText().toString();
            sPurchaseUnitPrice = PurchaseUnitPrice.getText().toString();
            sSalesUnitPrice = SalesUnitPrice.getText().toString();
            sRK_Notes = RK_Notes.getText().toString();
            s_measureent_en = measurement_en.getText().toString();
            s_meausrement_ar = measurement_ar.getText().toString();

            Set<String> set;

             set = new HashSet<>(selected_color_list);
            selected_color_list.clear();
            selected_color_list.addAll(set);

            set = new HashSet<>(selected_additional_list);
            selected_additional_list.clear();
            selected_additional_list.addAll(set);

            set = new HashSet<>(selected_size_list);
            selected_size_list.clear();
            selected_size_list.addAll(set);

            s_size_price = ""; s_color_price = ""; s_addition_price= "";
            sRK_Colors = ""; sRK_Size = ""; sRK_Add = "";



            if (!selected_size_list.isEmpty()) for (String s: selected_size_list){

                s_size_price+= "," + sizes_price.get(s);
                sRK_Size+= "," + s;
            }
            else {

                sRK_Size+= ",";
                s_size_price+= "," ;
            }
            if (!selected_color_list.isEmpty())for (String s: selected_color_list){

                s_color_price+= "," + colors_price.get(s);
                sRK_Colors+= "," + s;
            }
            else{

                sRK_Colors+=",";
                s_color_price+= ",";
            }
            if (!selected_additional_list.isEmpty()) for (String s : selected_additional_list){

                s_addition_price+= "," +additions_price.get(s);
                sRK_Add+="," + s;
            }
            else{

                s_addition_price+= ",";
                sRK_Add+=",";
            }

            currentdate=new Date();
            dateformat = new SimpleDateFormat("dd-MM-yyyy");



            ShowProgress();
            productsPresenter.requestAddProducts(new Product(sRKPrdNameEN, sRKPrdNameAR, sRKPrdDescrEN, sRKPrdDescrAR,
                    sRKBranches_Id, sProductNumber, sProductUPC, sPurchaseUnitPrice, sSalesUnitPrice, sProductPic,
                    sRK_Categories, sRK_Size.substring(1), sRK_Colors.substring(1), sRK_Add.substring(1) , String.valueOf(is_available),
                    sRK_Notes, "createdBy", dateformat.format(currentdate), "modifiedBy", dateformat.format(currentdate) ,
                    s_measureent_en , s_meausrement_ar , s_color_price.substring(1) , s_addition_price.substring(1), s_size_price.substring(1)));
        }
    }

    @Override
    public void onFinished(String result) {

        Toast.makeText(this, getResources().getString(R.string.succ_add_product), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFinished(int m) {

        Toast.makeText(this, getResources().getString(m), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddImgFinished() {

        addProduct();
    }

    @Override
    public void onFinished(List result) {

        spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, result);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
        RKBranches_Id.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onFinished(List<Addition> result, String req) {


        switch (req) {

            case "color" : {

                colors_ids.addAll(result);
                colors_adapter.notifyDataSetChanged();
                break;
            }

            case "category" : {

                spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, result);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                RK_Categories.setAdapter(spinnerArrayAdapter);
                break;
            }

            case "size" : {

                sizes_ids.addAll(result);
                size_adapter.notifyDataSetChanged();
                break;
            }

            case "add" : {

                add_ids.addAll(result);
                additional_adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onFailure(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int error) {

        Toast.makeText(this, getResources().getString(error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ShowProgress() {

        showProgressDialog();

    }

    @Override
    public void HideProgress() {

        hideProgressDialog();
    }


    class AdditionsAdapter extends RecyclerView.Adapter<AdditionsAdapter.MyViewHolder> {
        private final Context context;
        private final List<Addition> additionList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            Context context;
            private final CheckBox checkBox;
            private final EditText price;


            public MyViewHolder(View view) {
                super(view);
                checkBox = view.findViewById(R.id.check_box);
                price = view.findViewById(R.id.price);
                context = itemView.getContext();


            }
        }


        public AdditionsAdapter(Context context, List<Addition> additionList) {
            this.context = context;
            this.additionList = additionList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.addition_check_box_item, parent, false);

            return new AdditionsAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdditionsAdapter.MyViewHolder holder, final int position) {

            final Addition addition = additionList.get(position);

            if (addition.getClrNameAR()!=null) holder.checkBox.setText(addition.getClrNameAR());
            else if (addition.getSizeNameAR()!=null) holder.checkBox.setText(addition.getSizeNameAR());
            else holder.checkBox.setText(addition.getAdditionalAr());

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked){

                        holder.price.setVisibility(View.VISIBLE);

                        if (addition.getClrNameAR()!=null && !selected_color_list.contains(addition.getID()))
                            selected_color_list.add(addition.getID());
                        else if (addition.getSizeNameAR()!=null&& !selected_size_list.contains(addition.getID()))
                            selected_size_list.add(addition.getID());
                        else if (addition.getAdditionalAr()!=null && !selected_additional_list.contains(addition.getID()))
                            selected_additional_list.add(addition.getID());
                    }
                    else {

                        holder.price.setVisibility(View.INVISIBLE);

                        if (addition.getClrNameAR()!=null) selected_color_list.remove(addition.getID());
                        else if (addition.getSizeNameAR()!=null) selected_size_list.remove(addition.getID());
                        else selected_additional_list.remove(addition.getID());
                    }

                }
            });

            holder.price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (!hasFocus){

                        if (!TextUtils.isEmpty(holder.price.getText())) {

                            if (addition.getClrNameAR() != null)
                                colors_price.put(addition.getID(), holder.price.getText().toString());
                            else if (addition.getSizeNameAR() != null)
                                sizes_price.put(addition.getID(), holder.price.getText().toString());
                            else additions_price.put(addition.getID(), holder.price.getText().toString());
                        }
                        else {

                            if (addition.getClrNameAR() != null)
                                colors_price.remove(addition.getID());
                            else if (addition.getSizeNameAR() != null)
                                sizes_price.remove(addition.getID());
                            else additions_price.remove(addition.getID());
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return additionList.size();
        }


    }

}