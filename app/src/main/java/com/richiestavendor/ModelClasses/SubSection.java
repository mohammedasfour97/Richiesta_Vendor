package com.richiestavendor.ModelClasses;

public class SubSection {

    private String ID, CPSubSections, ARSubSections, ENSubSections, FRSubSections, Notes, Status, Ranking, ARDescription, ENDescription, FRDescription, ARImage, ENImage, FRImage, ID_Stores, ID_Branches, ID_USER,
            DateEdite, DateCreate ;

    public SubSection() {
    }

    public SubSection(String ID, String CPSubSections, String ARSubSections, String ENSubSections, String FRSubSections, String notes,
                      String status, String ranking, String ARDescription, String ENDescription, String FRDescription, String ARImage,
                      String ENImage, String FRImage, String ID_Stores, String ID_Branches, String ID_USER, String dateEdite, String dateCreate) {
        this.ID = ID;
        this.CPSubSections = CPSubSections;
        this.ARSubSections = ARSubSections;
        this.ENSubSections = ENSubSections;
        this.FRSubSections = FRSubSections;
        Notes = notes;
        Status = status;
        Ranking = ranking;
        this.ARDescription = ARDescription;
        this.ENDescription = ENDescription;
        this.FRDescription = FRDescription;
        this.ARImage = ARImage;
        this.ENImage = ENImage;
        this.FRImage = FRImage;
        this.ID_Stores = ID_Stores;
        this.ID_Branches = ID_Branches;
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

    public String getCPSubSections() {
        return CPSubSections;
    }

    public void setCPSubSections(String CPSubSections) {
        this.CPSubSections = CPSubSections;
    }

    public String getARSubSections() {
        return ARSubSections;
    }

    public void setARSubSections(String ARSubSections) {
        this.ARSubSections = ARSubSections;
    }

    public String getENSubSections() {
        return ENSubSections;
    }

    public void setENSubSections(String ENSubSections) {
        this.ENSubSections = ENSubSections;
    }

    public String getFRSubSections() {
        return FRSubSections;
    }

    public void setFRSubSections(String FRSubSections) {
        this.FRSubSections = FRSubSections;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRanking() {
        return Ranking;
    }

    public void setRanking(String ranking) {
        Ranking = ranking;
    }

    public String getARDescription() {
        return ARDescription;
    }

    public void setARDescription(String ARDescription) {
        this.ARDescription = ARDescription;
    }

    public String getENDescription() {
        return ENDescription;
    }

    public void setENDescription(String ENDescription) {
        this.ENDescription = ENDescription;
    }

    public String getFRDescription() {
        return FRDescription;
    }

    public void setFRDescription(String FRDescription) {
        this.FRDescription = FRDescription;
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
