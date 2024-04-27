package pe.edu.upc.sellmaster.inventory_service.service;

import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.edu.upc.sellmaster.inventory_service.model.dtos.*;
import pe.edu.upc.sellmaster.inventory_service.model.entities.Inventory;
import pe.edu.upc.sellmaster.inventory_service.repository.InventoryRepository;
import pe.edu.upc.sellmaster.inventory_service.repository.ProductClient;
import pe.edu.upc.sellmaster.inventory_service.repository.UserClient;

import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    @Transactional
    public void addInventory(InventoryRequest request) {
        ProductResponse product = productClient.getProductById(request.getProductID());
        UserResponse user = userClient.getUserById(request.getUserID());

        Inventory inventory = Inventory.builder()
                .productID(product.getProductID())
                .userID(user.getUserId())
                .stock(request.getStock())
                .build();
        inventoryRepository.save(inventory);
        log.info("Inventory added: {}", inventory);
    }

    public List<InventoryDetailResponse> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventory -> {
                    ProductResponse productResponse = productClient.getProductById(inventory.getProductID());
                    UserResponse userResponse = userClient.getUserById(inventory.getUserID());
                    return new InventoryDetailResponse(
                            inventory.getInventoryID(),
                            productResponse,
                            userResponse,
                            inventory.getStock());
                })
                .collect(Collectors.toList());
    }

    public InventoryResponse getInventoryById(long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        return new InventoryResponse(inventory.getInventoryID(),
                inventory.getProductID(),
                inventory.getUserID(),
                inventory.getStock());
    }

    @Transactional
    public void updateInventory(long id, InventoryRequest request) {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));

        existingInventory.setProductID(request.getProductID());
        existingInventory.setUserID(request.getUserID());
        existingInventory.setStock(request.getStock());
        inventoryRepository.save(existingInventory);
        log.info("Updated Inventory: {}", existingInventory);
    }

    public void deleteInventory(long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Inventory not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
        log.info("Deleted Inventory with id {}", id);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return ResponseEntity.status(e.status()).body(e.contentUTF8());
    }

}
