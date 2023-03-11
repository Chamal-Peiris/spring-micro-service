package com.chamal.Order.service.controller;

import com.chamal.Order.service.dto.OrderRequestDto;
import com.chamal.Order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws IllegalAccessException {
        orderService.PlaceOrder(orderRequestDto);
        return "Order Saved Successfully";
    }
}
