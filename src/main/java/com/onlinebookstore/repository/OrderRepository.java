package com.onlinebookstore.repository;

import com.onlinebookstore.entity.Order;
import com.onlinebookstore.entity.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser_UserId(int userId);
}
