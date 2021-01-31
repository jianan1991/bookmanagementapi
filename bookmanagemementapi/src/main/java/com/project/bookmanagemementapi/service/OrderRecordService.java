package com.project.bookmanagemementapi.service;

import com.project.bookmanagemementapi.exception.OrderBookClosedException;
import com.project.bookmanagemementapi.exception.OrderBookNotFoundException;
import com.project.bookmanagemementapi.model.OrderRecord;
import com.project.bookmanagemementapi.util.OrderType;

import java.util.List;

public interface OrderRecordService {
    List<OrderRecord> getOrderRecordByOrderId(Long id) throws  OrderBookNotFoundException;
    String insertOrderRecordById(Long orderBookId, OrderType orderType, float price) throws OrderBookClosedException, OrderBookNotFoundException;
}
