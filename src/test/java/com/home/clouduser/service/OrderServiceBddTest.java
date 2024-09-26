package com.home.clouduser.service;

import com.home.clouduser.configs.DebugLoggerExtension;
import com.home.clouduser.dao.OrderDao;
import com.home.clouduser.entities.Order;
import jakarta.persistence.QueryTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLTimeoutException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j

@ExtendWith(MockitoExtension.class)
class OrderServiceBddTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderService orderService;

    private Order orderToTest;

    private Order orderDetails;

    @BeforeEach
    void beforeEach() {
        orderToTest = Order.builder().commodityName("ASUS ROG ZEPHYRUS").price(3L).userId(3L).build();
        orderDetails= Order.builder().commodityName("ASUS ROG ZEPHYRUS V3").price(4L).build();
    }

    @Test
    void updateOrder() {
        log.info("updateOrder test start");


        // ==== happy flow ==========================================================================
        // force the orderDao to return the orderToTest
        given(orderDao.findById(Mockito.anyLong())).willReturn(Optional.of(orderToTest));

        // let orderDao.save() successfully return the orderToTest
        given(orderDao.save(any(Order.class))).willAnswer(invocation -> invocation.getArgument(0));


        Order orderUpdated = orderService.updateOrder(101L, orderDetails);
        Mockito.verify(orderDao).findById(101L);
        //Mockito.verify(orderService).queryById(101L); // not work
        assertEquals(orderDetails.getCommodityName(), orderUpdated.getCommodityName());
        assertEquals(orderDetails.getPrice(), orderUpdated.getPrice());


        // ==== exception case 1 , order not found ============================================================================

        // clean all stubs for an object
        Mockito.reset(orderDao);

        given(orderDao.findById(Mockito.anyLong())).willThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> orderService.updateOrder(101L, orderDetails));

        // ==== exception case 1 , order not found ============================================================================
        Mockito.reset(orderDao);
        given(orderDao.findById(Mockito.anyLong())).willThrow(QueryTimeoutException.class);

        assertDoesNotThrow(() -> orderService.updateOrder(101L, orderDetails));
        Mockito.verify(orderDao).findById(101L);
        assertNull(orderService.updateOrder(101L, orderDetails));
    }


}

