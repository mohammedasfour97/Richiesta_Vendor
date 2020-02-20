package com.richiestavendor.ModelClasses;

public class Detail {

    private String ID, ID_Stores, CPName, ARName, ENName, ad_state, us_state, Ranking, ID_USER, DateEdite, DateCreate;

    public Detail(String ID, String ID_Stores, String CPName, String ARName, String ENName, String ad_state, String us_state, String ranking, String ID_USER,
                  String dateEdite, String dateCreate) {
        this.ID = ID;
        this.ID_Stores = ID_Stores;
        this.CPName = CPName;
        this.ARName = ARName;
        this.ENName = ENName;
        this.ad_state = ad_state;
        this.us_state = us_state;
        Ranking = ranking;
        this.ID_USER = ID_USER;
        DateEdite = dateEdite;
        DateCreate = dateCreate;
    }

    public Detail() {
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
