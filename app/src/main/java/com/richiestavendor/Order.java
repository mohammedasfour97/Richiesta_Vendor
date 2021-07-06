package com.richiestavendor;

public class Order {

    private String ID , SONumber , RK_Branch , BranchNameAR , BranchNameEN , isMainBranch , RK_Products , ProductColor , ColorUnitPrice ,
            ProductAdditionals , AdditionalUnitPrice , ProductSize , SizeUnitPrice , SalesUnitPrice , Qty , TotalPrice , OrderNotes ,
            OrderDate , IsComplete , RK_User , RK_User1 , IsRecieved , IsCustomerDelivered , ItemPrice , ShippingRate , TotalwShipping ,
            PaidAmount , DisplayName , DisplayNameAR ;

    public Order(String ID, String SONumber, String RK_Branch, String branchNameAR, String branchNameEN, String isMainBranch,
                 String RK_Products, String productColor, String colorUnitPrice, String productAdditionals, String additionalUnitPrice,
                 String productSize, String sizeUnitPrice, String salesUnitPrice, String qty, String totalPrice, String orderNotes,
                 String orderDate, String isComplete, String RK_User, String RK_User1, String isRecieved, String isCustomerDelivered,
                 String itemPrice, String shippingRate, String totalwShipping, String paidAmount, String displayName,
                 String displayNameAR) {
        this.ID = ID;
        this.SONumber = SONumber;
        this.RK_Branch = RK_Branch;
        BranchNameAR = branchNameAR;
        BranchNameEN = branchNameEN;
        this.isMainBranch = isMainBranch;
        this.RK_Products = RK_Products;
        ProductColor = productColor;
        ColorUnitPrice = colorUnitPrice;
        ProductAdditionals = productAdditionals;
        AdditionalUnitPrice = additionalUnitPrice;
        ProductSize = productSize;
        SizeUnitPrice = sizeUnitPrice;
        SalesUnitPrice = salesUnitPrice;
        Qty = qty;
        TotalPrice = totalPrice;
        OrderNotes = orderNotes;
        OrderDate = orderDate;
        IsComplete = isComplete;
        this.RK_User = RK_User;
        this.RK_User1 = RK_User1;
        IsRecieved = isRecieved;
        IsCustomerDelivered = isCustomerDelivered;
        ItemPrice = itemPrice;
        ShippingRate = shippingRate;
        TotalwShipping = totalwShipping;
        PaidAmount = paidAmount;
        DisplayName = displayName;
        DisplayNameAR = displayNameAR;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSONumber() {
        return SONumber;
    }

    public void setSONumber(String SONumber) {
        this.SONumber = SONumber;
    }

    public String getRK_Branch() {
        return RK_Branch;
    }

    public void setRK_Branch(String RK_Branch) {
        this.RK_Branch = RK_Branch;
    }

    public String getBranchNameAR() {
        return BranchNameAR;
    }

    public void setBranchNameAR(String branchNameAR) {
        BranchNameAR = branchNameAR;
    }

    public String getBranchNameEN() {
        return BranchNameEN;
    }

    public void setBranchNameEN(String branchNameEN) {
        BranchNameEN = branchNameEN;
    }

    public String getIsMainBranch() {
        return isMainBranch;
    }

    public void setIsMainBranch(String isMainBranch) {
        this.isMainBranch = isMainBranch;
    }

    public String getRK_Products() {
        return RK_Products;
    }

    public void setRK_Products(String RK_Products) {
        this.RK_Products = RK_Products;
    }

    public String getProductColor() {
        return ProductColor;
    }

    public void setProductColor(String productColor) {
        ProductColor = productColor;
    }

    public String getColorUnitPrice() {
        return ColorUnitPrice;
    }

    public void setColorUnitPrice(String colorUnitPrice) {
        ColorUnitPrice = colorUnitPrice;
    }

    public String getProductAdditionals() {
        return ProductAdditionals;
    }

    public void setProductAdditionals(String productAdditionals) {
        ProductAdditionals = productAdditionals;
    }

    public String getAdditionalUnitPrice() {
        return AdditionalUnitPrice;
    }

    public void setAdditionalUnitPrice(String additionalUnitPrice) {
        AdditionalUnitPrice = additionalUnitPrice;
    }

    public String getProductSize() {
        return ProductSize;
    }

    public void setProductSize(String productSize) {
        ProductSize = productSize;
    }

    public String getSizeUnitPrice() {
        return SizeUnitPrice;
    }

    public void setSizeUnitPrice(String sizeUnitPrice) {
        SizeUnitPrice = sizeUnitPrice;
    }

    public String getSalesUnitPrice() {
        return SalesUnitPrice;
    }

    public void setSalesUnitPrice(String salesUnitPrice) {
        SalesUnitPrice = salesUnitPrice;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getOrderNotes() {
        return OrderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        OrderNotes = orderNotes;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getIsComplete() {
        return IsComplete;
    }

    public void setIsComplete(String isComplete) {
        IsComplete = isComplete;
    }

    public String getRK_User() {
        return RK_User;
    }

    public void setRK_User(String RK_User) {
        this.RK_User = RK_User;
    }

    public String getRK_User1() {
        return RK_User1;
    }

    public void setRK_User1(String RK_User1) {
        this.RK_User1 = RK_User1;
    }

    public String getIsRecieved() {
        return IsRecieved;
    }

    public void setIsRecieved(String isRecieved) {
        IsRecieved = isRecieved;
    }

    public String getIsCustomerDelivered() {
        return IsCustomerDelivered;
    }

    public void setIsCustomerDelivered(String isCustomerDelivered) {
        IsCustomerDelivered = isCustomerDelivered;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getShippingRate() {
        return ShippingRate;
    }

    public void setShippingRate(String shippingRate) {
        ShippingRate = shippingRate;
    }

    public String getTotalwShipping() {
        return TotalwShipping;
    }

    public void setTotalwShipping(String totalwShipping) {
        TotalwShipping = totalwShipping;
    }

    public String getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getDisplayNameAR() {
        return DisplayNameAR;
    }

    public void setDisplayNameAR(String displayNameAR) {
        DisplayNameAR = displayNameAR;
    }
}
