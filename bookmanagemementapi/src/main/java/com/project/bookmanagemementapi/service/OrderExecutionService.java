package com.project.bookmanagemementapi.service;

import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.exception.OrderBookOpenException;
import com.project.bookmanagemementapi.model.OrderExecution;

import java.util.List;

public interface OrderExecutionService {

    List<OrderExecution> getAllOrderExecutionById(Long id)throws OrderBookNotFoundException;
    String insertOrderExecutionByBookId(Long orderBookId, int quantity, float price) throws OrderBookOpenException;
}
