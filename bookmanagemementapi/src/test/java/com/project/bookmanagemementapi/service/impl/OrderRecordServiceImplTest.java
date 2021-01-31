package com.project.bookmanagemementapi.service.impl;

import com.project.bookmanagemementapi.exception.OrderBookClosedException;
import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.exception.OrderBookOpenException;
import com.project.bookmanagemementapi.model.OrderBook;
import com.project.bookmanagemementapi.model.OrderRecord;
import com.project.bookmanagemementapi.repository.OrderBookRespository;
import com.project.bookmanagemementapi.service.Impl.OrderRecordServiceImpl;
import com.project.bookmanagemementapi.util.OrderType;
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
public class OrderRecordServiceImplTest {


    @Autowired
    private OrderRecordServiceImpl orderRecordServiceImpl;

    @MockBean
    private OrderBookRespository orderBookRespository;

    List<OrderBook> orderBookList = new ArrayList<OrderBook>();
    List<OrderRecord> orderRecords = new ArrayList<OrderRecord>();

    OrderBook orderBook;

    OrderRecord orderRecord;
    @BeforeEach
    public void setup(){
        orderBook = new OrderBook();
        orderBook.setInstrumentId(12);
        orderBook.setUserId("test123");



        orderRecord = new OrderRecord();

        orderRecord.setOrderId(Long.valueOf(2));
        orderRecord.setEntryDate(LocalDateTime.now());
        orderRecord.setPrice((float)2.30);
        orderRecord.setOrderType(OrderType.LimitOrder);
        orderRecords.add(orderRecord);

        orderBook.setOrderRecords(orderRecords);
        orderBookList.add(orderBook);
    }

    @Test
    public void getOrderRecordByOrderIdTest() throws OrderBookNotFoundException{
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        orderRecordServiceImpl.getOrderRecordByOrderId(Long.valueOf(1));
        Assert.assertEquals(orderRecordServiceImpl.getOrderRecordByOrderId(Long.valueOf(1)), orderRecords);
    }

    @Test
    public void insertOrderRecordByIdTest() throws OrderBookClosedException{
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        Assert.assertEquals(orderRecordServiceImpl.insertOrderRecordById(Long.valueOf(1), OrderType.LimitOrder, (float)0.23),"Order record has been successfully inserted!");
    }

    @Test
    public void insertOrderExecutiononOpenByIdTest() throws OrderBookOpenException {
        orderBook.setOpen(true);
        Mockito.when(orderBookRespository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderBook));
        try{
            orderRecordServiceImpl.insertOrderRecordById(Long.valueOf(1), OrderType.LimitOrder, (float)0.23);
        }catch (Exception ex){
            Assert.assertEquals(ex.getMessage(), "Book has been closed");
        }
    }
}
