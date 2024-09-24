package com.home.clouduser.service;

import com.home.clouduser.entities.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@Disabled // only could trigger by manual
class OrderServiceIntrusiveTest {

    @Autowired
    private OrderService orderService;


    @Test
    void updateOrder() {

        Order order = Order.builder().commodityName("ASUS ROG ZEPHYRUS").price(3L).userId(3L).build();
        order = orderService.createOrder(order);


        Order orderUpdated = Order.builder().commodityName("ASUS ROG ZEPHYRUS V3").price(4L).build();

        orderUpdated = orderService.updateOrder(order.getId(), orderUpdated);
        assertEquals("ASUS ROG ZEPHYRUS V3", orderUpdated.getCommodityName());
        assertEquals(4L, orderUpdated.getPrice());

        orderService.deleteOrder(order.getId());

    }



}
