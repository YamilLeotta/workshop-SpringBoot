package grupo.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupo.artifact.model.Product;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
}