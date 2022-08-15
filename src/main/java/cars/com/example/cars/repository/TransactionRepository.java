package cars.com.example.cars.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import cars.com.example.cars.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
}
