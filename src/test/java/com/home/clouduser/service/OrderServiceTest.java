package com.home.clouduser.service;

import com.home.clouduser.configs.DebugLoggerExtension;
import com.home.clouduser.dao.OrderDao;
import com.home.clouduser.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(DebugLoggerExtension.class)
class OrderServiceTest {

    //@Mock
    //private OrderDao orderDao;
//
    //@InjectMocks
    //private OrderService orderService;

    private Order orderToTest;

    private Order orderdetails;

    @BeforeEach
    void beforeEach() {
        orderToTest = Order.builder().commodityName("ASUS ROG ZEPHYRUS").price(3L).userId(3L).build();
        orderdetails= Order.builder().commodityName("ASUS ROG ZEPHYRUS V3").price(4L).build();
    }

    @Test
    void updateOrder() {
        log.info("updateOrder test start");
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        OrderService orderService = new OrderService();
        ReflectionTestUtils.setField(orderService, "orderDao", orderDao);

        // ==== happy flow ==========================================================================
        // force the orderDao to return the orderToTest
        Mockito.when(orderDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(orderToTest));

        /*
         *  invocation is a concept of the mockito invocation, it includes the context information of the mock
         *  e.g. the argument of the method,  return values
         *
         *  usually, you can use the argument(0) to get the first argument
         *
         *  some methods
         *  getArgument(int index): get the argument at index
         *  getArguments(): get all arguments
         *  getMethod(): get the method
         *  getMock(): get the mock object
         */

        // means let orderDao save order return the argument it receives
        Mockito.when(orderDao.save(Mockito.any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));


        Order orderUpdated = orderService.updateOrder(101L, orderdetails);
        assertEquals(orderdetails.getCommodityName(), orderUpdated.getCommodityName());
        assertEquals(orderdetails.getPrice(), orderUpdated.getPrice());


        // ==== exception case 1 , order not found ============================================================================
        Mockito.when(orderDao.findById(Mockito.anyLong()))
                .thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> orderService.updateOrder(101L, orderdetails));
    }
}