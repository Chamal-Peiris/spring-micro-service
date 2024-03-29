package com.chamal.Order.service.controller;

import com.chamal.Order.service.dto.OrderRequestDto;
import com.chamal.Order.service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws IllegalAccessException {
       return CompletableFuture.supplyAsync(()-> {
           try {
               return orderService.PlaceOrder(orderRequestDto);
           } catch (IllegalAccessException e) {
               throw new RuntimeException(e);
           }
       });

    }
    public CompletableFuture<String> fallbackMethod(OrderRequestDto orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong, please order after some time!");
    }
}
