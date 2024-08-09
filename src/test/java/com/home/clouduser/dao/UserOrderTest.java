package com.home.clouduser.dao;

import com.home.clouduser.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;


@SpringBootTest
@Slf4j
public class UserOrderTest {
    @Autowired
    private OrderDao orderDao;
    @Test
    void findByCommodityName() {
        log.info("Test findByUsername");
        Order order = orderDao.findByCommodityName("Apple 苹果 iPhone 12 ");
        log.info("Order: {}", order);
        Assertions.assertNotNull(order);
    }

    @Test
    void findById() {
        log.info("Test findById");
        Optional<Order> optional = orderDao.findById(101L);
        Order order = null;
        if (optional.isPresent()) {
            order = optional.get();
        }
        log.info("Order: {}", order);
        Assertions.assertNotNull(order);
    }
}
