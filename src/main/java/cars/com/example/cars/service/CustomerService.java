package cars.com.example.cars.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cars.com.example.cars.model.Customer;
import cars.com.example.cars.repository.CustomerRepository;
import cars.com.example.cars.request.AddCustomerRequest;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public Customer saveCustomer(AddCustomerRequest req) {
        return repository.save(req.toCustomer(req));
    }

    public Customer getCustomerById(Long customer_id) {
        return repository.findById(customer_id).orElse(null);
    }
}
