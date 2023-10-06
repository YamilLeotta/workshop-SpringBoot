package grupo.artifact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import grupo.artifact.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private String username;
    private String password;

    public User(UserDTO userDTO){
        this.name = userDTO.getName();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }

    public UserDTO toDTO(){
        return
            UserDTO
                .builder()
                .id(this.id)
                .name(this.name)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
