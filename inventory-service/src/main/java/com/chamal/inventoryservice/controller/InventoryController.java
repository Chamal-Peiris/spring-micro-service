package com.chamal.inventoryservice.controller;

import com.chamal.inventoryservice.dto.InventoryResponseDto;
import com.chamal.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> skuCode){

        return inventoryService.isInStock(skuCode);
    }

}
