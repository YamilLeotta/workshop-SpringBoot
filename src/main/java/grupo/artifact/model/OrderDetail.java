package grupo.artifact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import grupo.artifact.model.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = false // define el join, en caso de opcional false indica que deben existir el producto para que la orden sea devuelta, sería 'INNER JOIN'
    )
    @JoinColumn(name = "products_services_id") // Nombre de la FK
    private Product product_service;

    @Column(name = "orders_id")
    private Integer orders;
    private Integer quantity;
    private String name;
    private Float cost;
    private Float tax;
    private Float discount;
    private Float service_support;
    private Integer warranty_years;
    private Float warranty_cost;


    public OrderDetail(OrderDetailDTO orderDetailDTO){
        this.name = orderDetailDTO.getName();
        this.cost = orderDetailDTO.getCost();
        this.tax = orderDetailDTO.getTax();
        this.discount = orderDetailDTO.getDiscount();
        this.service_support = orderDetailDTO.getService_support();
        this.warranty_cost = orderDetailDTO.getWarranty_cost();

        this.orders = orderDetailDTO.getOrders_id();
        this.warranty_years = orderDetailDTO.getWarranty_years();
        this.quantity = orderDetailDTO.getQuantity();
    }

    public OrderDetailDTO toDTO(){
        return
            OrderDetailDTO
                .builder()
                .id(this.id)
                .orders_id(this.orders)
                .name(this.name)
                .cost(this.cost)
                .quantity(this.quantity)
                .tax(this.tax)
                .discount(this.discount)
                .service_support(this.service_support)
                .warranty_years(this.warranty_years)
                .warranty_cost(this.warranty_cost)
                .products_services_id(this.product_service.getId())
                .build();
    }
}