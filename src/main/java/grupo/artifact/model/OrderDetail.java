package grupo.artifact.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = false // define el join, en caso de opcional false indica que deben existir el producto para que la orden sea devuelta, sería 'INNER JOIN'
    )
    @JoinColumn(name = "products_services_id") // Nombre de la FK
    private Product product_service;

    private Integer quantity;
    private String name;
    private Float cost;
    private Float tax;
    private Float discount;
    private Float service_support;
    private Integer warranty_years;
    private Float warranty_cost;
}