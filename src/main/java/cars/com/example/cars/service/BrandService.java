package cars.com.example.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cars.com.example.cars.model.Brand;
import cars.com.example.cars.repository.BrandRepository;

@Service
public class BrandService {
    
    @Autowired
    BrandRepository repository;

    public Brand saveBrand(Brand brand) {
        return repository.save(brand);
    }

}
