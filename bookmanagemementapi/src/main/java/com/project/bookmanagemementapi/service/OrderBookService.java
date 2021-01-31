package com.project.bookmanagemementapi.service;

import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.model.OrderBook;

import java.util.List;
import java.util.Optional;

public interface OrderBookService {
    public List<OrderBook> getAllOrderBook();
    public Optional<OrderBook> getOneOrderBookById(Long Id);
    public String closeOrderBookById(Long Id) throws OrderBookNotFoundException;
    public Boolean createNewOrderBook(String userId, int instructmentId);



}
