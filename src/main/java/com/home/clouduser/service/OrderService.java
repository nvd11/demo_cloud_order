package com.home.clouduser.service;


import com.home.clouduser.dao.OrderDao;
import com.home.clouduser.entities.Order;
import jakarta.persistence.QueryTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    // this method is used to query order by id
    public Order queryById(Long id) {
        // if optional.isPresent() is false, it will throw NoSuchElementException
        return orderDao.findById(id).orElseThrow(() -> new NoSuchElementException("No such order by id: " + id));
        /*
        Optional<Order> optional = orderDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }else {
            throw new NoSuchElementException("No such order by id: " + id);
        }

        */
    }

    public Order updateOrder(Long orderId, Order orderDetails) {

        Order order = null;
        try{
            order = this.queryById(orderId);
        } catch (NoSuchElementException e) {
            log.error("Error in getting order by id...", e);
            throw e;
        } catch (QueryTimeoutException e) {
            log.error("timeout..", e);
            return null;
        }

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
