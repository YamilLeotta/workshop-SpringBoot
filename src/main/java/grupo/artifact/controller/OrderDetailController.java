package grupo.artifact.controller;

import org.springframework.web.bind.annotation.*;
import grupo.artifact.model.OrderDetail;
import grupo.artifact.model.dto.OrderDetailDTO;
import grupo.artifact.service.OrderDetailService;
import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetail saveOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        return this.orderDetailService.saveOrderDetail(orderDetailDTO);
    }
    
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getAllOrderDetail() {
        return this.orderDetailService.getAllOrderDetail();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetail> getOrderDetailByOrder(@PathVariable Integer id) {
        return this.orderDetailService.getOrderDetailByOrder(id);
    }
}
