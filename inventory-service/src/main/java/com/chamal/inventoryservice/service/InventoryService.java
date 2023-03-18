package com.chamal.inventoryservice.service;

import com.chamal.inventoryservice.dto.InventoryResponseDto;
import com.chamal.inventoryservice.model.Inventory;
import com.chamal.inventoryservice.repository.InventoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        log.info("Wait started");
        Thread.sleep(10000);
        log.info("wait ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponseDto.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
