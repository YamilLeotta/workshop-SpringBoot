package grupo.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupo.artifact.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}