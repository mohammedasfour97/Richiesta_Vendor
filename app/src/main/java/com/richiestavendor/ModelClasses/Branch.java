package com.richiestavendor.ModelClasses;

public class Branch {

    private String ID, ID_Stores, CPName, ARName, ENName, ARAddress, ENAddress, Longitude, Latitude, ID_Country, ID_City, ID_Region, Tel, ad_state,
            us_state, Ranking, ID_USER, DateEdite, DateCreate;


    public Branch() {
    }

    public Branch(String ID, String ID_Stores, String CPName, String ARName, String ENName, String ARAddress, String ENAddress, String longitude,
                  String latitude, String ID_Country, String ID_City, String ID_Region, String tel, String ad_state, String us_state, String ranking,
                  String ID_USER, String dateEdite, String dateCreate) {
        this.ID = ID;
        this.ID_Stores = ID_Stores;
        this.CPName = CPName;
        this.ARName = ARName;
        this.ENName = ENName;
        this.ARAddress = ARAddress;
        this.ENAddress = ENAddress;
        Longitude = longitude;
        Latitude = latitude;
        this.ID_Country = ID_Country;
        this.ID_City = ID_City;
        this.ID_Region = ID_Region;
        Tel = tel;
        this.ad_state = ad_state;
        this.us_state = us_state;
        Ranking = ranking;
        this.ID_USER = ID_USER;
        DateEdite = dateEdite;
        DateCreate = dateCreate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_Stores() {
        return ID_Stores;
    }

    public void setID_Stores(String ID_Stores) {
        this.ID_Stores = ID_Stores;
    }

    public String getCPName() {
        return CPName;
    }

    public void setCPName(String CPName) {
        this.CPName = CPName;
    }

    public String getARName() {
        return ARName;
    }

    public void setARName(String ARName) {
        this.ARName = ARName;
    }

    public String getENName() {
        return ENName;
    }

    public void setENName(String ENName) {
        this.ENName = ENName;
    }

    public String getARAddress() {
        return ARAddress;
    }

    public void setARAddress(String ARAddress) {
        this.ARAddress = ARAddress;
    }

    public String getENAddress() {
        return ENAddress;
    }

    public void setENAddress(String ENAddress) {
        this.ENAddress = ENAddress;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getID_Country() {
        return ID_Country;
    }

    public void setID_Country(String ID_Country) {
        this.ID_Country = ID_Country;
    }

    public String getID_City() {
        return ID_City;
    }

    public void setID_City(String ID_City) {
        this.ID_City = ID_City;
    }

    public String getID_Region() {
        return ID_Region;
    }

    public void setID_Region(String ID_Region) {
        this.ID_Region = ID_Region;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getAd_state() {
        return ad_state;
    }

    public void setAd_state(String ad_state) {
        this.ad_state = ad_state;
    }

    public String getUs_state() {
        return us_state;
    }

    public void setUs_state(String us_state) {
        this.us_state = us_state;
    }

    public String getRanking() {
        return Ranking;
    }

    public void setRanking(String ranking) {
        Ranking = ranking;
    }

    public String getID_USER() {
        return ID_USER;
    }

    public void setID_USER(String ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getDateEdite() {
        return DateEdite;
    }

    public void setDateEdite(String dateEdite) {
        DateEdite = dateEdite;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }
}
