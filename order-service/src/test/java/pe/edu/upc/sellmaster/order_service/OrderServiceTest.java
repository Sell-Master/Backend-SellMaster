package pe.edu.upc.sellmaster.order_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.upc.sellmaster.order_service.model.dtos.ClientResponse;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderRequest;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderResponse;
import pe.edu.upc.sellmaster.order_service.model.dtos.UserResponse;
import pe.edu.upc.sellmaster.order_service.model.entities.Order;
import pe.edu.upc.sellmaster.order_service.repository.ClientClient;
import pe.edu.upc.sellmaster.order_service.repository.OrderRepository;
import pe.edu.upc.sellmaster.order_service.repository.UserClient;
import pe.edu.upc.sellmaster.order_service.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientClient clientClient; // Feign client mock
    @Mock
    private UserClient userClient; // Feign client mock

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test para agregar una orden
    @Test
    public void testAddOrder() {
        OrderRequest request = new OrderRequest(new Date(), 200.0, 1L, 1L);
        Order order = Order.builder()
                .orderID(1)
                .fecha(request.getFecha())
                .montoTotal(request.getMontoTotal())
                .clientID(request.getClientID())
                .userID(request.getUserID())
                .build();
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(clientClient.getClientById(anyLong())).thenReturn(new ClientResponse());
        when(userClient.getUserById(anyLong())).thenReturn(new UserResponse());

        OrderResponse response = orderService.addOrder(request);

        verify(orderRepository).save(order);
        verify(clientClient).getClientById(request.getClientID());
        verify(userClient).getUserById(request.getUserID());
        assertNotNull(response);
    }

    // Test para obtener todas las ordenes
    @Test
    public void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(1L, new Date(), 300.0, 1L, 1L)));
        when(clientClient.getClientById(anyLong())).thenReturn(new ClientResponse());
        when(userClient.getUserById(anyLong())).thenReturn(new UserResponse());

        List<OrderResponse> responses = orderService.getAllOrders();
        assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
    }

    // Test para obtener una orden por ID
    @Test
    public void testGetOrderById() {
        Order order = new Order(1L, new Date(), 300.0, 1L, 1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(clientClient.getClientById(anyLong())).thenReturn(new ClientResponse());
        when(userClient.getUserById(anyLong())).thenReturn(new UserResponse());

        OrderResponse response = orderService.getOrderById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getOrderID());
    }

    // Test para actualizar una orden
    @Test
    public void testUpdateOrder() {
        Order existingOrder = new Order(1L, new Date(), 300.0, 1L, 1L);
        OrderRequest request = new OrderRequest(new Date(), 350.0, 1L, 1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(existingOrder);
        when(clientClient.getClientById(anyLong())).thenReturn(new ClientResponse());
        when(userClient.getUserById(anyLong())).thenReturn(new UserResponse());

        OrderResponse updatedResponse = orderService.updateOrder(1L, request);

        assertEquals(350.0, updatedResponse.getMontoTotal());
        verify(orderRepository).save(existingOrder);
    }

    // Test para eliminar una orden
    @Test
    public void testDeleteOrder() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository).deleteById(1L);
    }
}
