package grupo.artifact.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import grupo.artifact.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products_services")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Float cost;
    
    private Float support;

    @OneToMany(mappedBy = "product_service")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<OrderDetail> orderDetails;

    public Product(ProductDTO productDTO){
        this.name = productDTO.getName();
        this.type = productDTO.getType();
        this.cost = productDTO.getCost();
        this.support = productDTO.getSupport();
    }

    public ProductDTO toDTO(){
        return
            ProductDTO
                .builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .cost(this.cost)
                .support(this.support)
                .build();
    }
}


