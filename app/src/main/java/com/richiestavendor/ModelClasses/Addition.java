package com.richiestavendor.ModelClasses;

import androidx.annotation.NonNull;

public class Addition {

    String title, ColorHex , ClrNameAR , ClrNameEN , ClrNotes , CreatedBy , CreatedDate , ModifiedBy , ModifiedDate , SizeNameEN , SizeNameAR , SizeUnitType ,
            SizeNotes, TagNameAR , TagNameEN , RK_Products , TagNotes , CategoryNameEN , CategoryNameAr , ParentCategory , CategoryNotes , Additional ,
            AdditionalAr , id , ID;

    public Addition() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAdditional() {
        return Additional;
    }

    public void setAdditional(String additional) {
        Additional = additional;
    }

    public String getAdditionalAr() {
        return AdditionalAr;
    }

    public void setAdditionalAr(String additionalAr) {
        AdditionalAr = additionalAr;
    }

    public String getColorHex() {
        return ColorHex;
    }

    public void setColorHex(String colorHex) {
        ColorHex = colorHex;
    }

    public String getClrNameAR() {
        return ClrNameAR;
    }

    public void setClrNameAR(String clrNameAR) {
        ClrNameAR = clrNameAR;
    }

    public String getClrNameEN() {
        return ClrNameEN;
    }

    public void setClrNameEN(String clrNameEN) {
        ClrNameEN = clrNameEN;
    }

    public String getClrNotes() {
        return ClrNotes;
    }

    public void setClrNotes(String clrNotes) {
        ClrNotes = clrNotes;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public String getCategoryNameEN() {
        return CategoryNameEN;
    }

    public void setCategoryNameEN(String categoryNameEN) {
        CategoryNameEN = categoryNameEN;
    }

    public String getCategoryNameAr() {
        return CategoryNameAr;
    }

    public void setCategoryNameAr(String categoryNameAr) {
        CategoryNameAr = categoryNameAr;
    }

    public String getParentCategory() {
        return ParentCategory;
    }

    public void setParentCategory(String parentCategory) {
        ParentCategory = parentCategory;
    }

    public String getCategoryNotes() {
        return CategoryNotes;
    }

    public void setCategoryNotes(String categoryNotes) {
        CategoryNotes = categoryNotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSizeNameEN() {
        return SizeNameEN;
    }

    public void setSizeNameEN(String sizeNameEN) {
        SizeNameEN = sizeNameEN;
    }

    public String getSizeNameAR() {
        return SizeNameAR;
    }

    public void setSizeNameAR(String sizeNameAR) {
        SizeNameAR = sizeNameAR;
    }

    public String getSizeUnitType() {
        return SizeUnitType;
    }

    public void setSizeUnitType(String sizeUnitType) {
        SizeUnitType = sizeUnitType;
    }

    public String getSizeNotes() {
        return SizeNotes;
    }

    public void setSizeNotes(String sizeNotes) {
        SizeNotes = sizeNotes;
    }

    public String getTagNameAR() {
        return TagNameAR;
    }

    public void setTagNameAR(String tagNameAR) {
        TagNameAR = tagNameAR;
    }

    public String getTagNameEN() {
        return TagNameEN;
    }

    public void setTagNameEN(String tagNameEN) {
        TagNameEN = tagNameEN;
    }

    public String getRK_Products() {
        return RK_Products;
    }

    public void setRK_Products(String RK_Products) {
        this.RK_Products = RK_Products;
    }

    public String getTagNotes() {
        return TagNotes;
    }

    public void setTagNotes(String tagNotes) {
        TagNotes = tagNotes;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
