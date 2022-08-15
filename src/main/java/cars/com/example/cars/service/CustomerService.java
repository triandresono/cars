package cars.com.example.cars.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cars.com.example.cars.model.Customer;
import cars.com.example.cars.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public List<Customer> inquiryCustomer(String param){
        return repository.inquiryCustomer(param);
    }
}
