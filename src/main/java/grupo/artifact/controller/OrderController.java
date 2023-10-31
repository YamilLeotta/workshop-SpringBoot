package grupo.artifact.controller;

import org.springframework.web.bind.annotation.*;

import grupo.artifact.model.Order;
import grupo.artifact.model.dto.OrderDetailDTO;
import grupo.artifact.service.OrderService;

import java.util.List;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order saveOrder(@RequestBody List<OrderDetailDTO> orderDetailDTOs) {
        return this.orderService.saveOrder(orderDetailDTOs);
    }
}
