package pe.edu.upc.sellmaster.order_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.sellmaster.order_service.model.dtos.ClientResponse;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderRequest;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderResponse;
import pe.edu.upc.sellmaster.order_service.model.dtos.UserResponse;
import pe.edu.upc.sellmaster.order_service.model.entities.Order;
import pe.edu.upc.sellmaster.order_service.repository.ClientClient;
import pe.edu.upc.sellmaster.order_service.repository.OrderRepository;
import pe.edu.upc.sellmaster.order_service.repository.UserClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientClient clientClient; // Assume Feign client exists
    private final UserClient userClient; // Assume Feign client exists

    @Transactional
    public OrderResponse addOrder(OrderRequest request) {
        // Optionally validate client and user
        clientClient.getClientById(request.getClientID());
        userClient.getUserById(request.getUserID());

        Order order = Order.builder()
                .fecha(request.getFecha())
                .montoTotal(request.getMontoTotal())
                .clientID(request.getClientID())
                .userID(request.getUserID())
                .build();

        Order savedOrder = orderRepository.save(order);
        log.info("Order added: {}", savedOrder);
        return mapToOrderResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToOrderResponse(order);
    }

    @Transactional
    public OrderResponse updateOrder(long id, OrderRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        existingOrder.setFecha(request.getFecha());
        existingOrder.setMontoTotal(request.getMontoTotal());
        existingOrder.setClientID(request.getClientID());
        existingOrder.setUserID(request.getUserID());

        Order updatedOrder = orderRepository.save(existingOrder);
        log.info("Updated Order: {}", updatedOrder);
        return mapToOrderResponse(updatedOrder);
    }

    public void deleteOrder(long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
        log.info("Deleted Order with id: {}", id);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        ClientResponse clientResponse = clientClient.getClientById(order.getClientID());
        UserResponse userResponse = userClient.getUserById(order.getUserID());

        return OrderResponse.builder()
                .orderID(order.getOrderID())
                .fecha(order.getFecha())
                .montoTotal(order.getMontoTotal())
                .client(clientResponse) // Assuming ClientResponse includes client details
                .user(userResponse) // Assuming UserResponse includes user details
                .build();
    }
}

