package pe.edu.upc.sellmaster.order_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderDetailRequest;
import pe.edu.upc.sellmaster.order_service.model.dtos.OrderDetailResponse;
import pe.edu.upc.sellmaster.order_service.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailResponse addOrderDetail(@RequestBody OrderDetailRequest request) {
        return orderDetailService.addOrderDetail(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetailResponse getOrderDetailById(@PathVariable("id") long id) {
        return orderDetailService.getOrderDetailById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetailResponse updateOrderDetail(@PathVariable("id") long id, @RequestBody OrderDetailRequest request) {
        return orderDetailService.updateOrderDetail(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderDetail(@PathVariable("id") long id) {
        orderDetailService.deleteOrderDetail(id);
    }
}
