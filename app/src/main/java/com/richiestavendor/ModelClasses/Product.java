package com.richiestavendor.ModelClasses;

public class Product {

    private String ID, ID_Stores, ID_Branches, ID_SubSections, CPName, ARName, ENName, FRName, ARSummary, ENSummary, FRSummary, ARDetails,
            ENDetails, FRDetails, ARUse, ENUse, FRUse, ARImage, ENImage, FRImage, Code, Status, sales, Views, Searches, Comments, Ratings, AvgRating,
            Notes, Ranking, ID_USER, DateEdite, DateCreate;

    public Product() {
    }

    public Product(String ID, String ID_Stores, String ID_Branches, String ID_SubSections, String CPName, String ARName, String ENName, String FRName,
                   String ARSummary, String ENSummary, String FRSummary, String ARDetails, String ENDetails, String FRDetails, String ARUse,
                   String ENUse, String FRUse, String ARImage, String ENImage, String FRImage, String code, String status, String sales, String views,
                   String searches, String comments, String ratings, String avgRating, String notes, String ranking, String ID_USER, String dateEdite,
                   String dateCreate) {
        this.ID = ID;
        this.ID_Stores = ID_Stores;
        this.ID_Branches = ID_Branches;
        this.ID_SubSections = ID_SubSections;
        this.CPName = CPName;
        this.ARName = ARName;
        this.ENName = ENName;
        this.FRName = FRName;
        this.ARSummary = ARSummary;
        this.ENSummary = ENSummary;
        this.FRSummary = FRSummary;
        this.ARDetails = ARDetails;
        this.ENDetails = ENDetails;
        this.FRDetails = FRDetails;
        this.ARUse = ARUse;
        this.ENUse = ENUse;
        this.FRUse = FRUse;
        this.ARImage = ARImage;
        this.ENImage = ENImage;
        this.FRImage = FRImage;
        Code = code;
        Status = status;
        this.sales = sales;
        Views = views;
        Searches = searches;
        Comments = comments;
        Ratings = ratings;
        AvgRating = avgRating;
        Notes = notes;
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

    public String getID_Branches() {
        return ID_Branches;
    }

    public void setID_Branches(String ID_Branches) {
        this.ID_Branches = ID_Branches;
    }

    public String getID_SubSections() {
        return ID_SubSections;
    }

    public void setID_SubSections(String ID_SubSections) {
        this.ID_SubSections = ID_SubSections;
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

    public String getFRName() {
        return FRName;
    }

    public void setFRName(String FRName) {
        this.FRName = FRName;
    }

    public String getARSummary() {
        return ARSummary;
    }

    public void setARSummary(String ARSummary) {
        this.ARSummary = ARSummary;
    }

    public String getENSummary() {
        return ENSummary;
    }

    public void setENSummary(String ENSummary) {
        this.ENSummary = ENSummary;
    }

    public String getFRSummary() {
        return FRSummary;
    }

    public void setFRSummary(String FRSummary) {
        this.FRSummary = FRSummary;
    }

    public String getARDetails() {
        return ARDetails;
    }

    public void setARDetails(String ARDetails) {
        this.ARDetails = ARDetails;
    }

    public String getENDetails() {
        return ENDetails;
    }

    public void setENDetails(String ENDetails) {
        this.ENDetails = ENDetails;
    }

    public String getFRDetails() {
        return FRDetails;
    }

    public void setFRDetails(String FRDetails) {
        this.FRDetails = FRDetails;
    }

    public String getARUse() {
        return ARUse;
    }

    public void setARUse(String ARUse) {
        this.ARUse = ARUse;
    }

    public String getENUse() {
        return ENUse;
    }

    public void setENUse(String ENUse) {
        this.ENUse = ENUse;
    }

    public String getFRUse() {
        return FRUse;
    }

    public void setFRUse(String FRUse) {
        this.FRUse = FRUse;
    }

    public String getARImage() {
        return ARImage;
    }

    public void setARImage(String ARImage) {
        this.ARImage = ARImage;
    }

    public String getENImage() {
        return ENImage;
    }

    public void setENImage(String ENImage) {
        this.ENImage = ENImage;
    }

    public String getFRImage() {
        return FRImage;
    }

    public void setFRImage(String FRImage) {
        this.FRImage = FRImage;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getViews() {
        return Views;
    }

    public void setViews(String views) {
        Views = views;
    }

    public String getSearches() {
        return Searches;
    }

    public void setSearches(String searches) {
        Searches = searches;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }

    public String getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(String avgRating) {
        AvgRating = avgRating;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
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
