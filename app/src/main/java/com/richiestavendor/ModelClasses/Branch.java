package com.richiestavendor.ModelClasses;

import androidx.annotation.NonNull;

public class Branch {


    private String NameAR;
    private String NameEN;
    private String Feild;
    private String RK_StoresContact;
    private String RK_Stores;
    private String RK_branchNotes;
    private String CreatedBy;
    private String CreatedDate;
    private String ModifiedBy;
    private String ModifiedDate;
    private String RK_Branch_ID;
    private String RK_Address;
    private String RK_City;
    private String RK_Country;
    private String RK_Phone;
    private String RK_Cell;
    private String RK_Email;
    private String RK_StoreNotes;
    private String Createdby;
    private String country_name_ar;
    private String country_name_en;
    private String city_name_ar;
    private String city_name_en;

    public String getRKBranchDetails_ID() {
        return RKBranchDetails_ID;
    }

    public void setRKBranchDetails_ID(String RKBranchDetails_ID) {
        this.RKBranchDetails_ID = RKBranchDetails_ID;
    }

    private String RKBranchDetails_ID;


    public Branch() {
    }

    public Branch(String nameAR, String nameEN, String feild, String RK_StoresContact, String RK_Stores, String RK_branchNotes, String createdBy,
                  String createdDate, String modifiedBy, String modifiedDate, String RK_Branch_ID, String RK_Address, String RK_City, String RK_Country,
                  String RK_Phone, String RK_Cell, String RK_Email, String RK_StoreNotes, String createdby) {
        NameAR = nameAR;
        NameEN = nameEN;
        Feild = feild;
        this.RK_StoresContact = RK_StoresContact;
        this.RK_Stores = RK_Stores;
        this.RK_branchNotes = RK_branchNotes;
        CreatedBy = createdBy;
        CreatedDate = createdDate;
        ModifiedBy = modifiedBy;
        ModifiedDate = modifiedDate;
        this.RK_Branch_ID = RK_Branch_ID;
        this.RK_Address = RK_Address;
        this.RK_City = RK_City;
        this.RK_Country = RK_Country;
        this.RK_Phone = RK_Phone;
        this.RK_Cell = RK_Cell;
        this.RK_Email = RK_Email;
        this.RK_StoreNotes = RK_StoreNotes;
        Createdby = createdby;
    }

    public String getNameAR() {
        return NameAR;
    }

    public void setNameAR(String nameAR) {
        NameAR = nameAR;
    }

    public String getNameEN() {
        return NameEN;
    }

    public void setNameEN(String nameEN) {
        NameEN = nameEN;
    }

    public String getFeild() {
        return Feild;
    }

    public void setFeild(String feild) {
        Feild = feild;
    }

    public String getRK_StoresContact() {
        return RK_StoresContact;
    }

    public void setRK_StoresContact(String RK_StoresContact) {
        this.RK_StoresContact = RK_StoresContact;
    }

    public String getRK_Stores() {
        return RK_Stores;
    }

    public void setRK_Stores(String RK_Stores) {
        this.RK_Stores = RK_Stores;
    }

    public String getRK_branchNotes() {
        return RK_branchNotes;
    }

    public void setRK_branchNotes(String RK_branchNotes) {
        this.RK_branchNotes = RK_branchNotes;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getRK_Branch_ID() {
        return RK_Branch_ID;
    }

    public void setRK_Branch_ID(String RK_Branch_ID) {
        this.RK_Branch_ID = RK_Branch_ID;
    }

    public String getRK_Address() {
        return RK_Address;
    }

    public void setRK_Address(String RK_Address) {
        this.RK_Address = RK_Address;
    }

    public String getRK_City() {
        return RK_City;
    }

    public void setRK_City(String RK_City) {
        this.RK_City = RK_City;
    }

    public String getRK_Country() {
        return RK_Country;
    }

    public void setRK_Country(String RK_Country) {
        this.RK_Country = RK_Country;
    }

    public String getRK_Phone() {
        return RK_Phone;
    }

    public void setRK_Phone(String RK_Phone) {
        this.RK_Phone = RK_Phone;
    }

    public String getRK_Cell() {
        return RK_Cell;
    }

    public void setRK_Cell(String RK_Cell) {
        this.RK_Cell = RK_Cell;
    }

    public String getRK_Email() {
        return RK_Email;
    }

    public void setRK_Email(String RK_Email) {
        this.RK_Email = RK_Email;
    }

    public String getRK_StoreNotes() {
        return RK_StoreNotes;
    }

    public void setRK_StoreNotes(String RK_StoreNotes) {
        this.RK_StoreNotes = RK_StoreNotes;
    }

    public String getCreatedby() {
        return Createdby;
    }

    public void setCreatedby(String createdby) {
        Createdby = createdby;
    }

    public String getCountry_name_ar() {
        return country_name_ar;
    }

    public void setCountry_name_ar(String country_name_ar) {
        this.country_name_ar = country_name_ar;
    }

    public String getCountry_name_en() {
        return country_name_en;
    }

    public void setCountry_name_en(String country_name_en) {
        this.country_name_en = country_name_en;
    }

    public String getCity_name_ar() {
        return city_name_ar;
    }

    public void setCity_name_ar(String city_name_ar) {
        this.city_name_ar = city_name_ar;
    }

    public String getCity_name_en() {
        return city_name_en;
    }

    public void setCity_name_en(String city_name_en) {
        this.city_name_en = city_name_en;
    }

    @NonNull
    @Override
    public String toString() {
        return  this.getNameAR();
    }
}
