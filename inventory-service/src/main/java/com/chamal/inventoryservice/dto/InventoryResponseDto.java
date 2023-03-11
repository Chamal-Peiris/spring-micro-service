package com.chamal.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder public class InventoryResponseDto implements Serializable {
    private String skuCode;
    private Boolean isInStock;
}
