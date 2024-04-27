package pe.edu.upc.sellmaster.order_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderDetailRequest;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderDetailResponse;
import pe.edu.upc.sellmaster.order_service.model.dtos.ProductResponse;
import pe.edu.upc.sellmaster.order_service.model.entities.OrderDetail;
import pe.edu.upc.sellmaster.order_service.repository.OrderDetailRepository;
import pe.edu.upc.sellmaster.order_service.repository.ProductClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderDetailResponse addOrderDetail(OrderDetailRequest request) {
        productClient.getProductById(request.getProductID()); // Validate existence of product

        OrderDetail orderDetail = OrderDetail.builder()
                .productID(request.getProductID())
                .orderID(request.getOrderID())
                .cantidad(request.getCantidad())
                .build();

        orderDetail = orderDetailRepository.save(orderDetail);
        log.info("OrderDetail added: {}", orderDetail);
        return mapToOrderDetailResponse(orderDetail);
    }

    public List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(this::mapToOrderDetailResponse)
                .collect(Collectors.toList());
    }

    public OrderDetailResponse getOrderDetailById(long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found with id: " + id));
        return mapToOrderDetailResponse(orderDetail);
    }

    @Transactional
    public OrderDetailResponse updateOrderDetail(long id, OrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found with id: " + id));

        orderDetail.setProductID(request.getProductID());
        orderDetail.setOrderID(request.getOrderID());
        orderDetail.setCantidad(request.getCantidad());

        orderDetail = orderDetailRepository.save(orderDetail);
        log.info("Updated OrderDetail: {}", orderDetail);
        return mapToOrderDetailResponse(orderDetail);
    }

    public void deleteOrderDetail(long id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new RuntimeException("OrderDetail not found with id: " + id);
        }
        orderDetailRepository.deleteById(id);
        log.info("Deleted OrderDetail with id: {}", id);
    }

    private OrderDetailResponse mapToOrderDetailResponse(OrderDetail orderDetail) {
        ProductResponse productResponse = productClient.getProductById(orderDetail.getProductID());

        return OrderDetailResponse.builder()
                .orderDetailID(orderDetail.getOrderDetailID())
                .product(productResponse)
                .cantidad(orderDetail.getCantidad())
                .orderID(orderDetail.getOrderID())
                .build();
    }
}

