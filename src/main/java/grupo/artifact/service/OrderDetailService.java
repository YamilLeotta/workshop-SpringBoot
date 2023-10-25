package grupo.artifact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import grupo.artifact.model.OrderDetail;
import grupo.artifact.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getAllOrderDetail() {
        return this.orderDetailRepository
            .findAll()
            .stream()
            .collect(Collectors.toList());
    }

    public List<OrderDetail> getOrderDetailByOrder(Integer id) {
        return this.orderDetailRepository
            .findByOrder(id)
            .stream()
            .collect(Collectors.toList());
    }
}