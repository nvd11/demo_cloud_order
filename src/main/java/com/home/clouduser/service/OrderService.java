package com.home.clouduser.service;


import com.home.clouduser.dao.OrderDao;
import com.home.clouduser.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    // this method is used to query order by id
    public Order queryById(Long id) {
        // if optional.isPresent() is false, it will throw NoSuchElementException
        Optional<Order> optional = orderDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }else {
            throw new NoSuchElementException("No such order by id: " + id);
        }
    }

    public Order updateOrder(Long orderId, Order orderDetails) {

        Order order = orderDao.findById(orderId).orElseThrow(() -> new NoSuchElementException("No such order by id: " + orderId));

        order.setCommodityName(orderDetails.getCommodityName());
        order.setPrice(orderDetails.getPrice());

        return orderDao.save(order);
    }

    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderDao.deleteById(orderId);
    }
}
