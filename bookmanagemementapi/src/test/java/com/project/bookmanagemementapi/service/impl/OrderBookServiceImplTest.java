package com.project.bookmanagemementapi.service.impl;

import com.project.bookmanagemementapi.model.OrderBook;
import com.project.bookmanagemementapi.repository.OrderBookRespository;
import com.project.bookmanagemementapi.service.Impl.OrderBookServiceImpl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderBookServiceImplTest {

    @Autowired
    private OrderBookServiceImpl orderBookServiceImpl;

    @MockBean
    private OrderBookRespository orderBookRespository;

    List<OrderBook> orderBookList = new ArrayList<OrderBook>();

    OrderBook orderBook;

    @BeforeEach
    public void setup(){
        orderBook = new OrderBook();
        orderBook.setInstrumentId(12);
        orderBook.setUserId("test123");
        orderBookList.add(orderBook);
    }


    @Test
    public void testCreateNewOrderBook(){
        OrderBook orderBook = new OrderBook();
        orderBook.setInstrumentId(12);
        orderBook.setUserId("test123");
        Assert.assertEquals(orderBookServiceImpl.createNewOrderBook("Jack",12),true);
    }
    @Test
    public void testGetAllOrderBook(){

        Mockito.when(orderBookRespository.findAll()).thenReturn(orderBookList);
        Assert.assertEquals(1, orderBookServiceImpl.getAllOrderBook().size());

    }

    @Test
    public void testGetOneOrderBookById(){
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        Assert.assertEquals(Optional.of(orderBook), orderBookRespository.findById(Long.valueOf(1)));
    }

    @Test
    public void testcloseOrderBookById(){
        Mockito.when(orderBookRespository.closeOrderBook(1)).thenReturn(1);
        Assert.assertEquals(1, orderBookRespository.closeOrderBook(1));
    }
}
