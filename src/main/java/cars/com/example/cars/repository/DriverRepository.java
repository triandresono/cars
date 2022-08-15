package cars.com.example.cars.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cars.com.example.cars.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>  {
    @Query(value = "SELECT * FROM driver_table d WHERE d.driver_name LIKE :param% OR d.id_number LIKE :param%", nativeQuery = true)
    List<Driver> inquiryDriver(@Param("param") String param);
}
