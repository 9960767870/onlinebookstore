package com.onlinebookstore.Services;

import com.onlinebookstore.entity.Order;
import com.onlinebookstore.entity.OrderDTO;
import com.onlinebookstore.entity.OrderRequest;
import com.onlinebookstore.exceptionhandling.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    String placeOrder(OrderRequest orderRequest) throws CustomException;
    List<OrderDTO> getOrdersByUser (int userId);
}
