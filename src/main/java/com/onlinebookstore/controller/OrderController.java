package com.onlinebookstore.controller;

import com.onlinebookstore.Services.OrderService;
import com.onlinebookstore.entity.OrderDTO;
import com.onlinebookstore.entity.OrderRequest;
import com.onlinebookstore.exceptionhandling.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) throws CustomException {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }

    @GetMapping("/getordersbyuser/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable int userId ){
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }


}
