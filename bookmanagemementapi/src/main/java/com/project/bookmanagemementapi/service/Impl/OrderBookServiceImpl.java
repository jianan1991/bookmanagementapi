package com.project.bookmanagemementapi.service.Impl;

import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.repository.OrderBookRespository;
import com.project.bookmanagemementapi.service.OrderBookService;
import com.project.bookmanagemementapi.model.OrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderBookServiceImpl implements OrderBookService {

    OrderBookRespository orderBookRespository;

    private static Logger log = LoggerFactory.getLogger(OrderBookServiceImpl.class);

    @Autowired
    public OrderBookServiceImpl(OrderBookRespository orderBookRespository){
        this.orderBookRespository = orderBookRespository;
    }

    @Override
    public List<OrderBook> getAllOrderBook() {
        return orderBookRespository.findAll();
    }

    @Override
    public Optional<OrderBook> getOneOrderBookById(Long Id) {
        return orderBookRespository.findById(Id);
    }

    @Override
    public String closeOrderBookById(Long Id) throws OrderBookNotFoundException{
        if (orderBookRespository.closeOrderBook(Id)!=1){
            throw new OrderBookNotFoundException("Order book is not found");
        }
        return "Success";
    }

    @Override
    public Boolean createNewOrderBook(String userId, int instructmentId){
        log.info("{} is creating a new order book for {}", userId, instructmentId);
        OrderBook orderBook = new OrderBook();
        orderBook.setUserId(userId);
        orderBook.setInstrumentId(instructmentId);
        orderBookRespository.save(orderBook);
        log.info("{} has successfully created a new order book for {}", userId, instructmentId);
        return true;
    }


}
