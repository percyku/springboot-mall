package com.kupercy.model;

import java.util.Date;

public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}
