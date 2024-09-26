package com.home.clouduser.controller;

import com.home.clouduser.dto.ApiResponse;
import com.home.clouduser.entities.Order;
import com.home.clouduser.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLTimeoutException;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/{id}")
    public Order queryById(@PathVariable("id") Long id) {
        return orderService.queryById(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Order>> queryByIdResponseEntity(@PathVariable("id") Long id) {
        Order order = null;
        try {
            order = orderService.queryById(id);
            ApiResponse<Order> response = new ApiResponse<>();
            response.setData(order);
            response.setReturnCode(0);
            response.setReturnMsg("Order fetched successfully ...");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in queryById...", e);
            ApiResponse<Order> response = new ApiResponse<>();
            response.setReturnCode(-1);
            response.setReturnMsg("Error in fetching order: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
