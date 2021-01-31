package com.project.bookmanagemementapi.controller;

import com.project.bookmanagemementapi.service.OrderBookService;
import com.project.bookmanagemementapi.model.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("OrderBook")
public class OrderBookController {

    @Autowired
    OrderBookService orderBookService;


    @GetMapping("/getAllOrderBook")
    public ResponseEntity<List<OrderBook>> getAllOrderBook (){
        return ResponseEntity.ok(orderBookService.getAllOrderBook());
    }

    @GetMapping("/GetOrderBookById/{id}")
    public ResponseEntity<Optional<OrderBook>> getOrderBookById (@PathVariable("id") Long id){
        return ResponseEntity.ok(orderBookService.getOneOrderBookById(id));
    }

    @PutMapping("/closeBookById/{id}")
    public ResponseEntity<String> closeOrderBookById (@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(orderBookService.closeOrderBookById(id));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/createOrderBook")
    public ResponseEntity<Boolean> createNewOrderBook (String userId, int instructmentId){
        return ResponseEntity.ok(orderBookService.createNewOrderBook(userId, instructmentId));
    }
}
