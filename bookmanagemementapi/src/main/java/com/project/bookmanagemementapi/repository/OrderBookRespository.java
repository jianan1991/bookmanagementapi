package com.project.bookmanagemementapi.repository;

import com.project.bookmanagemementapi.model.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRespository extends JpaRepository<OrderBook, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE order_book SET is_open = false WHERE order_book_id=?1", nativeQuery=true)
    int closeOrderBook(long orderId);


}
