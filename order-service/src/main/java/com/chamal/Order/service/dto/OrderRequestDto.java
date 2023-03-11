package com.chamal.Order.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto implements Serializable {
     List<OrderLineItemsDto> orderLineItemsDtoList;
}
