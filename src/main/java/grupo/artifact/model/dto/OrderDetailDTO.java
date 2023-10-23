package grupo.artifact.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDTO {
    private Integer id;
    private Integer orders_id;
    private Integer products_services_id;
    private Integer quantity;
    private String name;
    private Float cost;
    private Float tax;
    private Float discount;
    private Float service_support;
    private Integer warranty_years;
    private Float warranty_cost;
}