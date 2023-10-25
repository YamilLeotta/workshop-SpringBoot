package grupo.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo.artifact.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}