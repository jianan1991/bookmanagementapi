package com.project.bookmanagemementapi.service.impl;

import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.exception.OrderBookOpenException;
import com.project.bookmanagemementapi.model.OrderBook;
import com.project.bookmanagemementapi.model.OrderExecution;
import com.project.bookmanagemementapi.repository.OrderBookRespository;
import com.project.bookmanagemementapi.service.OrderExecutionService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderExecutionServiceImplTest {

    @Autowired
    private OrderExecutionService orderExecutionServiceImpl;

    @MockBean
    private OrderBookRespository orderBookRespository;

    List<OrderBook> orderBookList = new ArrayList<OrderBook>();
    List<OrderExecution> orderExecutions = new ArrayList<OrderExecution>();

    OrderBook orderBook;

    OrderExecution orderExecution;
    @BeforeEach
    public void setup(){
        orderBook = new OrderBook();
        orderBook.setInstrumentId(12);
        orderBook.setUserId("test123");
        orderBook.setOpen(false);
        orderExecution = new OrderExecution();

        orderExecution.setExecutionDate(LocalDateTime.now());
        orderExecution.setQuantity(2);
        orderExecution.setPrice((float)9.30);
        orderExecutions.add(orderExecution);

        orderBook.setOrderExecutions(orderExecutions);
        orderBookList.add(orderBook);
    }

    @Test
    public void getOrderRecordByOrderIdTest() throws OrderBookNotFoundException {
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        orderExecutionServiceImpl.getAllOrderExecutionById(Long.valueOf(1));
        Assert.assertEquals(orderExecutionServiceImpl.getAllOrderExecutionById(Long.valueOf(1)), orderExecutions);
    }

    @Test
    public void insertOrderExecutionByIdTest() throws OrderBookOpenException {
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        Assert.assertEquals(orderExecutionServiceImpl.insertOrderExecutionByBookId(Long.valueOf(1), 2, (float)0.23),"Execution record has been successfully inserted!");
    }

    @Test
    public void insertOrderExecutiononOpenByIdTest() throws OrderBookOpenException {
        orderBook.setOpen(true);
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        try{
            orderExecutionServiceImpl.insertOrderExecutionByBookId(Long.valueOf(1), 2, (float)0.23);
        }catch (Exception ex){
            Assert.assertEquals(ex.getMessage(), "Book is still open");
        }
    }
}
