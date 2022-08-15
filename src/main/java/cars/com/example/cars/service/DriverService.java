package cars.com.example.cars.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cars.com.example.cars.model.Driver;
import cars.com.example.cars.repository.DriverRepository;

@Service
public class DriverService {
    @Autowired
    DriverRepository repository;

    public List<Driver> inquiryDriver(String param){
        return repository.inquiryDriver(param);
    }
}
