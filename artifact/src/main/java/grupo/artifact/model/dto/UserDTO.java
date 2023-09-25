package grupo.artifact.model.dto;

import lombok.Builder;

//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Builder
public class UserDTO {


    private String name;

//    @JsonProperty("usuario") // Especifica en que propiedad del JSON viene el dato
    private String username;
    private String password;
}
