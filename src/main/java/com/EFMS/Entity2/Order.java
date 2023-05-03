package com.EFMS.Entity2;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Order {
    private Long OrderId;
    private List<CartItem> cartItem;
    private int totalPrice;
    private int userId;
    private String addressId;
    private String status;
    private String dateOfOrder;
    private String updateOn;
    private String managerID;
    private String paymentId;

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(String updateOn) {
        this.updateOn = updateOn;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
 class CartItem {
    private ProductDetails item;
    private int qty;
    private int productId;

     public ProductDetails getItem() {
         return item;
     }

     public void setItem(ProductDetails item) {
         this.item = item;
     }

     public int getQty() {
         return qty;
     }

     public void setQty(int qty) {
         this.qty = qty;
     }

     public int getProductId() {
         return productId;
     }

     public void setProductId(int productId) {
         this.productId = productId;
     }
 }
