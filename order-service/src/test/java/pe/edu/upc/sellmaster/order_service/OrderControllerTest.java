package pe.edu.upc.sellmaster.order_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.order_service.controller.OrderController;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderRequest;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderResponse;
import pe.edu.upc.sellmaster.order_service.service.OrderService;

import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test para agregar una orden
    @Test
    public void testAddOrder() {
        OrderRequest request = new OrderRequest(new Date(), 200.0, 1L, 1L);
        OrderResponse expectedResponse = new OrderResponse(1L, new Date(), 200.0, null, null);
        when(orderService.addOrder(request)).thenReturn(expectedResponse);

        ResponseEntity<OrderResponse> response = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderController.addOrder(request));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.getOrderID(), response.getBody().getOrderID());
    }

    // Test para obtener todas las ordenes
    @Test
    public void testGetAllOrders() {
        List<OrderResponse> expectedResponses = List.of(new OrderResponse(1L, new Date(), 300.0, null, null));
        when(orderService.getAllOrders()).thenReturn(expectedResponses);

        ResponseEntity<List<OrderResponse>> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(orderController.getAllOrders());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    // Test para obtener una orden por ID
    @Test
    public void testGetOrderById() {
        OrderResponse expectedResponse = new OrderResponse(1L, new Date(), 300.0, null, null);
        when(orderService.getOrderById(1L)).thenReturn(expectedResponse);

        ResponseEntity<OrderResponse> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(orderController.getOrderById(1L));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.getOrderID(), response.getBody().getOrderID());
    }

    // Test para actualizar una orden
    @Test
    public void testUpdateOrder() {
        OrderRequest request = new OrderRequest(new Date(), 350.0, 1L, 1L);
        OrderResponse expectedResponse = new OrderResponse(1L, new Date(), 350.0, null, null);
        when(orderService.updateOrder(1L, request)).thenReturn(expectedResponse);

        ResponseEntity<OrderResponse> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(orderController.updateOrder(1L, request));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.getMontoTotal(), response.getBody().getMontoTotal());
    }

    // Test para eliminar una orden
    @Test
    public void testDeleteOrder() {
        doNothing().when(orderService).deleteOrder(1L);

        ResponseEntity<Void> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}

