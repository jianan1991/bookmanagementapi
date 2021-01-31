package com.project.bookmanagemementapi.controller;

import com.project.bookmanagemementapi.service.OrderExecutionService;
import com.project.bookmanagemementapi.model.OrderExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("OrderExecution")
public class OrderExecutionController {


    @Autowired
    OrderExecutionService orderExecutionService;


    @GetMapping("/getOrderExecutionByOrderId/{id}")
    public ResponseEntity<List<OrderExecution>> getOrderRecordsById (@RequestParam Long id){
        try{
            return ResponseEntity.ok(orderExecutionService.getAllOrderExecutionById(id));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/createNewExecution")
    public ResponseEntity<String> createNewOrderRecord(Long orderBookId, int quantity, float price){
        try{
            return ResponseEntity.ok(orderExecutionService.insertOrderExecutionByBookId(orderBookId, quantity, price));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
}
