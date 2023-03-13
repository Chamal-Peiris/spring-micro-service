package com.chamal.Order.service.service;

import com.chamal.Order.service.dto.InventoryResponseDto;
import com.chamal.Order.service.dto.OrderLineItemsDto;
import com.chamal.Order.service.dto.OrderRequestDto;
import com.chamal.Order.service.model.Order;
import com.chamal.Order.service.model.OrderLineItems;
import com.chamal.Order.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WebClient.Builder webClientBuilder;

    public void PlaceOrder(OrderRequestDto orderRequestDto) throws IllegalAccessException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();

        // Call inventry service and place order if product is in stock

        //Using webfulx to make async calls, (the block method will stop async calls and make it sync calls)

        //replaced localhost with inventory service name (eureka handles the ip part)
        InventoryResponseDto[] inventoryResponsArray = webClientBuilder.build().get().uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()).retrieve().bodyToMono(InventoryResponseDto[].class).block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsArray).allMatch(InventoryResponseDto::isInStock);

        //should be opposite
        if (!allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalAccessException("Product is not in stock please try again later");
        }

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;

    }
}
