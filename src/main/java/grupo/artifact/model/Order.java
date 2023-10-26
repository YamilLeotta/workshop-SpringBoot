package grupo.artifact.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer users_id;

    @Column(nullable = false)
    private Integer clients_id;

    @Column(nullable = false)
    private LocalDate creation_date;

    @PrePersist
    public void prePersist() {
        creation_date = LocalDate.now();
    }

    @OneToMany(mappedBy = "order")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Set<OrderDetail> orderDetails;
}