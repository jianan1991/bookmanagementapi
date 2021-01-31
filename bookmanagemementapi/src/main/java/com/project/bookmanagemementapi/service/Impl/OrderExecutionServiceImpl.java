package com.project.bookmanagemementapi.service.Impl;

import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.exception.OrderBookOpenException;
import com.project.bookmanagemementapi.repository.OrderBookRespository;
import com.project.bookmanagemementapi.service.OrderExecutionService;
import com.project.bookmanagemementapi.model.OrderBook;
import com.project.bookmanagemementapi.model.OrderExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderExecutionServiceImpl implements OrderExecutionService{
    private static Logger log = LoggerFactory.getLogger(OrderExecutionServiceImpl.class);

    OrderBookRespository orderBookRespository;

    @Autowired
    public OrderExecutionServiceImpl(OrderBookRespository orderBookRespository){
        this.orderBookRespository = orderBookRespository;
    }


    @Override
    public List<OrderExecution> getAllOrderExecutionById(Long id) throws OrderBookNotFoundException{
        Optional<OrderBook> orderBook = orderBookRespository.findById(id);

        if(orderBook.isPresent()){
            return orderBook.get().getOrderExecutions();
        }else{
            throw new OrderBookNotFoundException("Order book is not found");
        }
    }

    @Override
    public String insertOrderExecutionByBookId(Long orderBookId, int quantity, float price) throws OrderBookOpenException{
        OrderBook orderBook = orderBookRespository.findById(orderBookId).get();

        if(orderBook != null){
            if(!orderBook.getOpen()){
                log.info("{} is inserting Order Record ", orderBook.getUserId());

                List<OrderExecution> orderExecutions = orderBook.getOrderExecutions();
                OrderExecution orderExecution = new OrderExecution();
                orderExecution.setExecutionDate(LocalDateTime.now());
                orderExecution.setPrice(price);
                orderExecution.setQuantity(quantity);
                orderExecutions.add(orderExecution);
                orderBookRespository.save(orderBook);
                log.info("{} has successfully inserted Execution Order ", orderBook.getUserId());

            }else{
                throw new OrderBookOpenException("Book is still open");
            }

        }
        return "Execution record has been successfully inserted!";

    }
}
