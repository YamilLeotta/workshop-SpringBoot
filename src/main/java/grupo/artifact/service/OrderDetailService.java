package grupo.artifact.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import grupo.artifact.exception.custom.NotCreatedException;
import grupo.artifact.model.OrderDetail;
import grupo.artifact.model.Product;
import grupo.artifact.model.dto.OrderDetailDTO;
import grupo.artifact.repository.OrderDetailRepository;
import grupo.artifact.repository.ProductRepository;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductRepository productRepository){
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }

    public OrderDetail saveOrderDetail(OrderDetailDTO orderDetailDTO){
        if (checkOrderDetailDTO(orderDetailDTO)) {
            Product product = this.productRepository.findById(orderDetailDTO.getProducts_services_id()).get();

            System.out.println(product.toString());

            OrderDetail orderDetail = new OrderDetail(
                null, // Null
                product,
                orderDetailDTO.getOrders_id(),
                orderDetailDTO.getQuantity(),
                product.getName(),
                product.getCost(),
                (float) (product.getCost() * .21), // El tax deberia obtenerse en base al cost y los impuestos del Product. Algo así: product.sum(taxes) * product.getCost()
                (float) (product.getCost() * .1), // El discount deberia obtenerse en base a porcentaje de regla de negocio y si el cliente tiene servicios contratados o en este detalle hay algun servicio.
                product.getSupport(),
                orderDetailDTO.getWarranty_years(),
                (float) (orderDetailDTO.getWarranty_years() * .02 * product.getCost()) // El cost de gtia. por regla de negocio, costo de producto y años de gtia.
            );

            System.out.println(orderDetail.toString() + "\n\n\n");
    
            return this.orderDetailRepository.save(orderDetail);
        }

        throw new NotCreatedException("OrderDetail hasn't been created");
    }

    public List<OrderDetailDTO> getAllOrderDetail() {
        return this.orderDetailRepository
            .findAll()
            .stream()
            .map(OrderDetail::toDTO)
            .collect(Collectors.toList());
    }

    public List<OrderDetail> getOrderDetailByOrder(Integer id) {
        return this.orderDetailRepository
            .findByOrders(id)
            .stream()
            .collect(Collectors.toList());
    }

    private Boolean checkOrderDetailDTO(OrderDetailDTO orderDetailDTO){
        return Boolean.TRUE;
    }
}