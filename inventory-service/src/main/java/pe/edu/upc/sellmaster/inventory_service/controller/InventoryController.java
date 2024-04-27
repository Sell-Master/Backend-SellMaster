package pe.edu.upc.sellmaster.inventory_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import pe.edu.upc.sellmaster.inventory_service.model.dtos.InventoryDetailResponse;
import pe.edu.upc.sellmaster.inventory_service.model.dtos.InventoryRequest;
import pe.edu.upc.sellmaster.inventory_service.model.dtos.InventoryResponse;
import pe.edu.upc.sellmaster.inventory_service.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new inventory", description = "Adds new inventory to the system")
    public void addInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.addInventory(inventoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all inventory", description = "Retrieves all inventory data")
    public List<InventoryDetailResponse> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get inventory by ID", description = "Retrieves a specific inventory by ID")
    public InventoryResponse getInventoryById(@PathVariable("id") long id) {
        return inventoryService.getInventoryById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update inventory", description = "Updates a specific inventory by ID")
    public void updateInventory(@PathVariable("id") long id, @RequestBody InventoryRequest inventoryRequest) {
        inventoryService.updateInventory(id, inventoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete inventory", description = "Deletes a specific inventory by ID")
    public void deleteInventory(@PathVariable("id") long id) {
        inventoryService.deleteInventory(id);
    }
}

