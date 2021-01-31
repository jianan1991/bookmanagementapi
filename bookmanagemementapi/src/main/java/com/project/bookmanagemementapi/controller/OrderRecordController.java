package com.project.bookmanagemementapi.controller;


import com.project.bookmanagemementapi.service.OrderRecordService;
import com.project.bookmanagemementapi.model.OrderRecord;
import com.project.bookmanagemementapi.util.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("OrderRecord")
public class OrderRecordController {


    @Autowired
    OrderRecordService orderRecordService;


    @GetMapping("/getOrderRecordByOrderId/{id}")
    public ResponseEntity<List<OrderRecord>> getOrderRecordsById (@RequestParam Long id){
        try{
            return ResponseEntity.ok(orderRecordService.getOrderRecordByOrderId(id));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/createNewOrderRecord")
    public ResponseEntity<String> createNewOrderRecord(Long orderBookId, OrderType orderType, float price){
        try{
            return ResponseEntity.ok(orderRecordService.insertOrderRecordById(orderBookId, orderType, price));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

}
