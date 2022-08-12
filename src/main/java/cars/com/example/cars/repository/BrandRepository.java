package cars.com.example.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cars.com.example.cars.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
