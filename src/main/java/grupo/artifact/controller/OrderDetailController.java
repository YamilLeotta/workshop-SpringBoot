package grupo.artifact.controller;

import org.springframework.web.bind.annotation.*;

import grupo.artifact.model.OrderDetail;
import grupo.artifact.service.OrderDetailService;
import grupo.artifact.service.ProductService;

import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService, ProductService productService) {
        this.orderDetailService = orderDetailService;
    }
    
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetail> getAllOrderDetail() {
        return this.orderDetailService.getAllOrderDetail();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetail> getOrderDetailByOrder(@PathVariable Integer id) {
        return this.orderDetailService.getOrderDetailByOrder(id);
    }
}
