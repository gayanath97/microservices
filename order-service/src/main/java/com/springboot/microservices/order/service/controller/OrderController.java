package com.springboot.microservices.order.service.controller;

import com.springboot.microservices.order.service.dto.OrderRequest;
import com.springboot.microservices.order.service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
    }
    public CompletableFuture<String> placeOrderFallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->"Ooops! Order cannot be placed at this moment. Please try again later") ;
    }
}
