package com.richiestavendor;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "store_table")
public class Store {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private String id ;
    @ColumnInfo(name = "Phone")
    private String phone;
    @ColumnInfo(name = "RK_Branch")
    private String RK_Branch;
    @ColumnInfo(name = "isMainBranch")
    private String is_main_branch;
    @ColumnInfo(name = "RK_Stores")
    private String RK_Stores;
    @ColumnInfo(name = "RKStoreNameAr")
    private String RK_store_nameAr ;
    @ColumnInfo(name = "RKStoreNameEN")
    private String RK_store_nameEN;
    @ColumnInfo(name = "RKStoreLogo")
    private String RK_store_logo;
    @ColumnInfo(name = "RK_Stores1")
    private String RK_stores1;
    @ColumnInfo(name = "IsStoreAdmin")
    private String is_store_admin;

    public Store(String id, String phone, String RK_Branch, String is_main_branch, String RK_Stores, String RK_store_nameAr,
                 String RK_store_nameEN, String RK_store_logo, String RK_stores1, String is_store_admin) {
        this.id = id;
        this.phone = phone;
        this.RK_Branch = RK_Branch;
        this.is_main_branch = is_main_branch;
        this.RK_Stores = RK_Stores;
        this.RK_store_nameAr = RK_store_nameAr;
        this.RK_store_nameEN = RK_store_nameEN;
        this.RK_store_logo = RK_store_logo;
        this.RK_stores1 = RK_stores1;
        this.is_store_admin = is_store_admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRK_Branch() {
        return RK_Branch;
    }

    public void setRK_Branch(String RK_Branch) {
        this.RK_Branch = RK_Branch;
    }

    public String getIs_main_branch() {
        return is_main_branch;
    }

    public void setIs_main_branch(String is_main_branch) {
        this.is_main_branch = is_main_branch;
    }

    public String getRK_Stores() {
        return RK_Stores;
    }

    public void setRK_Stores(String RK_Stores) {
        this.RK_Stores = RK_Stores;
    }

    public String getRK_store_nameAr() {
        return RK_store_nameAr;
    }

    public void setRK_store_nameAr(String RK_store_nameAr) {
        this.RK_store_nameAr = RK_store_nameAr;
    }

    public String getRK_store_nameEN() {
        return RK_store_nameEN;
    }

    public void setRK_store_nameEN(String RK_store_nameEN) {
        this.RK_store_nameEN = RK_store_nameEN;
    }

    public String getRK_store_logo() {
        return RK_store_logo;
    }

    public void setRK_store_logo(String RK_store_logo) {
        this.RK_store_logo = RK_store_logo;
    }

    public String getRK_stores1() {
        return RK_stores1;
    }

    public void setRK_stores1(String RK_stores1) {
        this.RK_stores1 = RK_stores1;
    }

    public String getIs_store_admin() {
        return is_store_admin;
    }

    public void setIs_store_admin(String is_store_admin) {
        this.is_store_admin = is_store_admin;
    }
}
