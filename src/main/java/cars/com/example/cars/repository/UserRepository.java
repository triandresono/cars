package cars.com.example.cars.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import cars.com.example.cars.model.Users;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);
  
    Boolean existsByEmail(String email);
}
