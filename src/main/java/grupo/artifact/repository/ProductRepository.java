package grupo.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo.artifact.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}