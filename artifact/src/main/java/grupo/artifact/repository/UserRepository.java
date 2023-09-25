package grupo.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo.artifact.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);
    Optional<User> findByUsernameAndPassword(String username, String password);
    
}
