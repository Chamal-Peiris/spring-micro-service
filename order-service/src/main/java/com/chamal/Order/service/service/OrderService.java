package com.chamal.Order.service.service;

import com.chamal.Order.service.dto.OrderLineItemsDto;
import com.chamal.Order.service.dto.OrderRequestDto;
import com.chamal.Order.service.model.Order;
import com.chamal.Order.service.model.OrderLineItems;
import com.chamal.Order.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public void PlaceOrder(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto).toList();


        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){

        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;

    }
}
