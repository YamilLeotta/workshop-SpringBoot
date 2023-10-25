package grupo.artifact.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import grupo.artifact.model.Order;
import grupo.artifact.model.OrderDetail;
import grupo.artifact.model.Product;
import grupo.artifact.model.dto.OrderDetailDTO;
import grupo.artifact.repository.OrderRepository;
import grupo.artifact.repository.ProductRepository;

@Service
@Transactional
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(List<OrderDetailDTO> orderDetailDTOs){
        Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
        Order order = new Order(null, 1, 1, null, null);

        Product product1 = productRepository
            .findById(orderDetailDTOs
                .get(0)
                .getProduct_service_id())
            .orElse(null);







        OrderDetail orderDetail1 = new OrderDetail(
            null,
            order,
            product1,
            orderDetailDTOs.get(0).getQuantity(),
            product1.getName(),
            product1.getCost(),
            (float) (product1.getCost() * .21), // El tax deberia obtenerse en base al cost y los impuestos del Product. Algo así: product.sum(taxes) * product.getCost()
            (float) (product1.getCost() * .1), // El discount deberia obtenerse en base a porcentaje de regla de negocio y si el cliente tiene servicios contratados o en este detalle hay algun servicio.
            product1.getSupport(), // En el caso de que sea un servicio se pasa esto, si no null
            orderDetailDTOs.get(0).getWarranty_years(),
            (orderDetailDTOs.get(0).getWarranty_years() != null) ? (float) (orderDetailDTOs.get(0).getWarranty_years() * .02 * product1.getCost()) : null // El cost de gtia. por regla de negocio, costo de producto y años de gtia.
        );
        orderDetails.add(orderDetail1);

        Product product2 = productRepository
            .findById(orderDetailDTOs
            .get(1)
            .getProduct_service_id())
            .orElse(null);
        OrderDetail orderDetail2 = new OrderDetail(
            null,
            order,
            product2,
            orderDetailDTOs.get(1).getQuantity(),
            product2.getName(),
            product2.getCost(),
            (float) (product2.getCost() * .21), // El tax deberia obtenerse en base al cost y los impuestos del Product. Algo así: product.sum(taxes) * product.getCost()
            (float) (product2.getCost() * .1), // El discount deberia obtenerse en base a porcentaje de regla de negocio y si el cliente tiene servicios contratados o en este detalle hay algun servicio.
            product2.getSupport(),
            orderDetailDTOs.get(1).getWarranty_years(),
            (orderDetailDTOs.get(1).getWarranty_years() != null) ? (float) (orderDetailDTOs.get(1).getWarranty_years() * .02 * product2.getCost()) : null // El cost de gtia. por regla de negocio, costo de producto y años de gtia.
        );
        orderDetails.add(orderDetail2);

        Product product3 = productRepository
            .findById(orderDetailDTOs
            .get(2)
            .getProduct_service_id())
            .orElse(null);
        OrderDetail orderDetail3 = new OrderDetail(
            null,
            order,
            product3,
            orderDetailDTOs.get(2).getQuantity(),
            product3.getName(),
            product3.getCost(),
            (float) (product3.getCost() * .21), // El tax deberia obtenerse en base al cost y los impuestos del Product. Algo así: product.sum(taxes) * product.getCost()
            (float) (product3.getCost() * .1), // El discount deberia obtenerse en base a porcentaje de regla de negocio y si el cliente tiene servicios contratados o en este detalle hay algun servicio.
            product3.getSupport(),
            orderDetailDTOs.get(2).getWarranty_years(),
            (orderDetailDTOs.get(2).getWarranty_years() != null) ? (float) (orderDetailDTOs.get(2).getWarranty_years() * .02 * product3.getCost()) : null // El cost de gtia. por regla de negocio, costo de producto y años de gtia.
        );
        orderDetails.add(orderDetail3);

        order.setOrderDetails(orderDetails);

        return orderRepository.save(order);
    }
}