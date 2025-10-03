package com.onlinebookstore.Services.impl;

import com.onlinebookstore.Services.OrderService;
import com.onlinebookstore.emailservice.EmailService;
import com.onlinebookstore.entity.*;
import com.onlinebookstore.exceptionhandling.CustomException;
import com.onlinebookstore.mapper.OrderMapper;
import com.onlinebookstore.repository.BookRepository;
import com.onlinebookstore.repository.OrderRepository;
import com.onlinebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public String placeOrder(OrderRequest orderRequest) throws CustomException {
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new CustomException("User not found"));
        Order order = new Order();
        order.setUser(user);
        order.setStatus("Placed");
        order.setOrderDate(LocalDateTime.now());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItems()) {
            Book book = bookRepository.findById(orderItemRequest.getBookId()).orElseThrow(() -> new CustomException("Book not found"));
            OrderItems  orderItem = new OrderItems();
            orderItem.setBook(book);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setOrder(order);
            orderItem.setItemPrice(book.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())));
            order.getOrderItems().add(orderItem);
            totalPrice=totalPrice.add(orderItem.getItemPrice());
        }
        order.setTotalPrice(totalPrice);

        Order save = orderRepository.save(order);
        if(save.getOrderId()>0){
            // Send confirmation email
            emailService.sendOrderConfirmationHtmlEmail(user, order);
            return "Order placed successfully. Order ID: "+save.getOrderId();
        }else{
            throw new CustomException("Failed to place order");
        }

    }

    @Override
    public List<OrderDTO> getOrdersByUser(int userId) {
       return orderMapper.toDTO(orderRepository.findByUser_UserId(userId));
    }
}
