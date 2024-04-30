package pe.edu.upc.sellmaster.order_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.order_service.controller.OrderDetailController;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderDetailRequest;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderDetailResponse;
import pe.edu.upc.sellmaster.order_service.model.dtos.ProductResponse;
import pe.edu.upc.sellmaster.order_service.service.OrderDetailService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderDetailControllerTest {

    @Mock
    private OrderDetailService orderDetailService;

    @InjectMocks
    private OrderDetailController orderDetailController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test for adding an order detail
    @Test
    public void testAddOrderDetail() {
        OrderDetailRequest request = new OrderDetailRequest(5, 2L, 1L);
        OrderDetailResponse expectedResponse = new OrderDetailResponse(1L, 5, new ProductResponse(), 1L);
        when(orderDetailService.addOrderDetail(request)).thenReturn(expectedResponse);

        OrderDetailResponse actualResponse = orderDetailController.addOrderDetail(request);

        verify(orderDetailService).addOrderDetail(request);
        assertEquals(expectedResponse.getOrderDetailID(), actualResponse.getOrderDetailID());
    }

    // Test for getting all order details
    @Test
    public void testGetAllOrderDetails() {
        List<OrderDetailResponse> expectedResponses = List.of(
                new OrderDetailResponse(1L, 10, new ProductResponse(), 1L)
        );
        when(orderDetailService.getAllOrderDetails()).thenReturn(expectedResponses);

        List<OrderDetailResponse> actualResponses = orderDetailController.getAllOrderDetails();

        assertEquals(1, actualResponses.size());
        assertEquals(10, actualResponses.get(0).getCantidad());
    }

    // Test for getting an order detail by ID
    @Test
    public void testGetOrderDetailById() {
        long id = 1L;
        OrderDetailResponse expectedResponse = new OrderDetailResponse(id, 10, new ProductResponse(), 1L);
        when(orderDetailService.getOrderDetailById(id)).thenReturn(expectedResponse);

        OrderDetailResponse actualResponse = orderDetailController.getOrderDetailById(id);

        assertEquals(id, actualResponse.getOrderDetailID());
    }

    // Test for updating an order detail
    @Test
    public void testUpdateOrderDetail() {
        long id = 1L;
        OrderDetailRequest request = new OrderDetailRequest(15, 3L, 2L);
        OrderDetailResponse expectedResponse = new OrderDetailResponse(id, 15, new ProductResponse(), 2L);
        when(orderDetailService.updateOrderDetail(id, request)).thenReturn(expectedResponse);

        OrderDetailResponse actualResponse = orderDetailController.updateOrderDetail(id, request);

        assertEquals(15, actualResponse.getCantidad());
        verify(orderDetailService).updateOrderDetail(id, request);
    }

    // Test for deleting an order detail
    @Test
    public void testDeleteOrderDetail() {
        long id = 1L;
        doNothing().when(orderDetailService).deleteOrderDetail(id);

        orderDetailController.deleteOrderDetail(id);

        verify(orderDetailService).deleteOrderDetail(id);
    }
}

