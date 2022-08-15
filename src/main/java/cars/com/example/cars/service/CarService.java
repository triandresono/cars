package cars.com.example.cars.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cars.com.example.cars.model.Car;
import cars.com.example.cars.repository.CarRepository;

@Service
public class CarService {
    @Autowired
    CarRepository repository;

    public List<Car> inquiryCar(String param){
        return repository.inquiryCar(param);
    }
}
