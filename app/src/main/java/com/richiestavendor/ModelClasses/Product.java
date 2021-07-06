package com.richiestavendor.ModelClasses;

import java.io.Serializable;

public class Product implements Serializable {

    private String id , RKPrdNameEN, RKPrdNameAR, RKPrdDescrEN, RKPrdDescrAR, RKBranches_Id, ProductNumber, ProductUPC, PurchaseUnitPrice,
            SalesUnitPrice, ProductPic, FRSummary, ARDetails, RK_Categories, RK_Size, RK_Colors, RK_Additins,  IsAvailable, RK_Notes,
            CreatedBy, CreatedDate, ModifiedBy, ModifiedDate , MeasurmentUnit , MeasurmentUnitAr, ColorPrice, AdditionalPrice , SizePrice;

    public Product() {
    }

    public Product(String RKPrdNameEN, String RKPrdNameAR, String RKPrdDescrEN, String RKPrdDescrAR, String RKBranches_Id,
                   String productNumber, String productUPC, String purchaseUnitPrice, String salesUnitPrice, String productPic,
                   String RK_Categories, String RK_Size, String RK_Colors, String RK_Additins,
                   String isAvailable, String RK_Notes, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
                   String measurmentUnit, String measurmentUnitAr, String colorPrice, String additionalPrice, String sizePrice) {
        this.RKPrdNameEN = RKPrdNameEN;
        this.RKPrdNameAR = RKPrdNameAR;
        this.RKPrdDescrEN = RKPrdDescrEN;
        this.RKPrdDescrAR = RKPrdDescrAR;
        this.RKBranches_Id = RKBranches_Id;
        ProductNumber = productNumber;
        ProductUPC = productUPC;
        PurchaseUnitPrice = purchaseUnitPrice;
        SalesUnitPrice = salesUnitPrice;
        ProductPic = productPic;
        this.FRSummary = FRSummary;
        this.ARDetails = ARDetails;
        this.RK_Categories = RK_Categories;
        this.RK_Size = RK_Size;
        this.RK_Colors = RK_Colors;
        this.RK_Additins = RK_Additins;
        IsAvailable = isAvailable;
        this.RK_Notes = RK_Notes;
        CreatedBy = createdBy;
        CreatedDate = createdDate;
        ModifiedBy = modifiedBy;
        ModifiedDate = modifiedDate;
        MeasurmentUnit = measurmentUnit;
        MeasurmentUnitAr = measurmentUnitAr;
        ColorPrice = colorPrice;
        AdditionalPrice = additionalPrice;
        SizePrice = sizePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRK_Additins() {
        return RK_Additins;
    }

    public void setRK_Additins(String RK_Additins) {
        this.RK_Additins = RK_Additins;
    }

    public String getRKPrdNameEN() {
        return RKPrdNameEN;
    }

    public void setRKPrdNameEN(String RKPrdNameEN) {
        this.RKPrdNameEN = RKPrdNameEN;
    }

    public String getRKPrdNameAR() {
        return RKPrdNameAR;
    }

    public void setRKPrdNameAR(String RKPrdNameAR) {
        this.RKPrdNameAR = RKPrdNameAR;
    }

    public String getRKPrdDescrEN() {
        return RKPrdDescrEN;
    }

    public void setRKPrdDescrEN(String RKPrdDescrEN) {
        this.RKPrdDescrEN = RKPrdDescrEN;
    }

    public String getRKPrdDescrAR() {
        return RKPrdDescrAR;
    }

    public void setRKPrdDescrAR(String RKPrdDescrAR) {
        this.RKPrdDescrAR = RKPrdDescrAR;
    }

    public String getRKBranches_Id() {
        return RKBranches_Id;
    }

    public void setRKBranches_Id(String RKBranches_Id) {
        this.RKBranches_Id = RKBranches_Id;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String productNumber) {
        ProductNumber = productNumber;
    }

    public String getProductUPC() {
        return ProductUPC;
    }

    public void setProductUPC(String productUPC) {
        ProductUPC = productUPC;
    }

    public String getPurchaseUnitPrice() {
        return PurchaseUnitPrice;
    }

    public void setPurchaseUnitPrice(String purchaseUnitPrice) {
        PurchaseUnitPrice = purchaseUnitPrice;
    }

    public String getSalesUnitPrice() {
        return SalesUnitPrice;
    }

    public void setSalesUnitPrice(String salesUnitPrice) {
        SalesUnitPrice = salesUnitPrice;
    }

    public String getProductPic() {
        return ProductPic;
    }

    public void setProductPic(String productPic) {
        ProductPic = productPic;
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

    public String getRK_Categories() {
        return RK_Categories;
    }

    public void setRK_Categories(String RK_Categories) {
        this.RK_Categories = RK_Categories;
    }

    public String getRK_Size() {
        return RK_Size;
    }

    public void setRK_Size(String RK_Size) {
        this.RK_Size = RK_Size;
    }

    public String getRK_Colors() {
        return RK_Colors;
    }

    public void setRK_Colors(String RK_Colors) {
        this.RK_Colors = RK_Colors;
    }

    public String getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        IsAvailable = isAvailable;
    }

    public String getRK_Notes() {
        return RK_Notes;
    }

    public void setRK_Notes(String RK_Notes) {
        this.RK_Notes = RK_Notes;
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

    public String getMeasurmentUnit() {
        return MeasurmentUnit;
    }

    public void setMeasurmentUnit(String measurmentUnit) {
        MeasurmentUnit = measurmentUnit;
    }

    public String getMeasurmentUnitAr() {
        return MeasurmentUnitAr;
    }

    public void setMeasurmentUnitAr(String measurmentUnitAr) {
        MeasurmentUnitAr = measurmentUnitAr;
    }

    public String getColorPrice() {
        return ColorPrice;
    }

    public void setColorPrice(String colorPrice) {
        ColorPrice = colorPrice;
    }

    public String getAdditionalPrice() {
        return AdditionalPrice;
    }

    public void setAdditionalPrice(String additionalPrice) {
        AdditionalPrice = additionalPrice;
    }

    public String getSizePrice() {
        return SizePrice;
    }

    public void setSizePrice(String sizePrice) {
        SizePrice = sizePrice;
    }
}
