package pe.edu.upc.sellmaster.inventory_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.inventory_service.controller.InventoryController;
import pe.edu.upc.sellmaster.inventory_service.model.dtos.*;
import pe.edu.upc.sellmaster.inventory_service.service.InventoryService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test for adding inventory
    @Test
    public void testAddInventory() {
        InventoryRequest inventoryRequest = new InventoryRequest(1L, 2L, 100);
        doNothing().when(inventoryService).addInventory(inventoryRequest);

        inventoryController.addInventory(inventoryRequest);

        verify(inventoryService).addInventory(inventoryRequest);
    }

    // Test for getting all inventory
    @Test
    public void testGetAllInventory() {
        InventoryDetailResponse expectedResponse = new InventoryDetailResponse(
                1L,
                new ProductResponse(1L, "Product Type", "Brand", "Additional Info", 20.00),
                new UserResponse(2L, "John", "Doe", "john@example.com", new TypeOfUserResponse(1L, "Admin")),
                100
        );
        List<InventoryDetailResponse> expectedResponses = List.of(expectedResponse);
        when(inventoryService.getAllInventory()).thenReturn(expectedResponses);

        List<InventoryDetailResponse> actualResponses = inventoryController.getAllInventory();

        assertEquals(1, actualResponses.size());
        assertEquals(100, actualResponses.get(0).getStock());
        assertEquals("John", actualResponses.get(0).getUser().getFirstName());
    }

    // Test for getting inventory by ID
    @Test
    public void testGetInventoryById() {
        long id = 1L;
        InventoryResponse expectedResponse = new InventoryResponse(
                id,
                new ProductResponse(1L, "Product Type", "Brand", "Additional Info", 20.00).getProductID(),
                new UserResponse(2L, "John", "Doe", "john@example.com", new TypeOfUserResponse(1L, "Admin")).getUserId(),
                100
        );
        when(inventoryService.getInventoryById(id)).thenReturn(expectedResponse);

        InventoryResponse actualResponse = inventoryController.getInventoryById(id);

        assertEquals(id, actualResponse.getInventoryID());
        assertEquals(2L, actualResponse.getUserID());
    }

    // Test for updating inventory
    @Test
    public void testUpdateInventory() {
        long id = 1L;
        InventoryRequest inventoryRequest = new InventoryRequest(1L, 2L, 150);
        doNothing().when(inventoryService).updateInventory(id, inventoryRequest);

        inventoryController.updateInventory(id, inventoryRequest);

        verify(inventoryService).updateInventory(id, inventoryRequest);
    }

    // Test for deleting inventory
    @Test
    public void testDeleteInventory() {
        long id = 1L;
        doNothing().when(inventoryService).deleteInventory(id);

        inventoryController.deleteInventory(id);

        verify(inventoryService).deleteInventory(id);
    }
}
