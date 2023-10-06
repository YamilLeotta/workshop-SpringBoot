package grupo.artifact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String name;
    private String type;
    private Float cost;
    private Float support;

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


