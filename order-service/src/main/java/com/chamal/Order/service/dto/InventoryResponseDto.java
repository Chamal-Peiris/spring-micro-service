package com.chamal.Order.service.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponseDto {
    private String skuCode;
    private boolean isInStock;
}