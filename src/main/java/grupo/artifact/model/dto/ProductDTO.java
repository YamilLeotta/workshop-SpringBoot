package grupo.artifact.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private String type;
    private Float cost;
    private Float support;
}