package grupo.artifact.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDTO {
    private Integer product_service_id;
    private Integer quantity;
    private Integer warranty_years;
}