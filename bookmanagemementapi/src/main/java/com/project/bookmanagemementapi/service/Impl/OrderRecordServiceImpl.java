package com.project.bookmanagemementapi.service.Impl;

import com.project.bookmanagemementapi.exception.OrderBookClosedException;
import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.repository.OrderBookRespository;
import com.project.bookmanagemementapi.service.OrderRecordService;
import com.project.bookmanagemementapi.model.OrderBook;
import com.project.bookmanagemementapi.model.OrderRecord;
import com.project.bookmanagemementapi.util.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderRecordServiceImpl implements OrderRecordService {
    OrderBookRespository orderBookRespository;

    private static Logger log = LoggerFactory.getLogger(OrderRecordServiceImpl.class);

    @Autowired
    public OrderRecordServiceImpl(OrderBookRespository orderBookRespository){
        this.orderBookRespository = orderBookRespository;
    }


    @Override
    public String insertOrderRecordById(Long orderBookId, OrderType orderType, float price) throws OrderBookClosedException{

        OrderBook orderBook = orderBookRespository.findById(orderBookId).get();
        if(orderBook != null){
            if(orderBook.getOpen()){
                log.info("{} is inserting Order Record ", orderBook.getUserId());
                List<OrderRecord> orderRecords = orderBook.getOrderRecords();
                OrderRecord orderRecord = new OrderRecord();
                orderRecord.setOrderType(orderType);
                orderRecord.setPrice(price);
                orderRecord.setEntryDate(LocalDateTime.now());
                orderRecords.add(orderRecord);
                orderBookRespository.save(orderBook);
                log.info("{} has successfully inserted Order Record ", orderBook.getUserId());

            }else{
                throw new OrderBookClosedException("Book has been closed");
            }

        }
        return "Order record has been successfully inserted!";

    }

    @Override
    public List<OrderRecord> getOrderRecordByOrderId(Long id) throws OrderBookNotFoundException {
        Optional<OrderBook> orderBook = orderBookRespository.findById(id);

        if(orderBook.isPresent()){
            return orderBook.get().getOrderRecords();
        }else{
            throw new OrderBookNotFoundException("Order book is not found");
        }
    }
}
