package com.onlinebookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class OrderRequest {

    private int userId;

    List<OrderItemRequest> orderItems;

    public OrderRequest() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
