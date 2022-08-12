package cars.com.example.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cars.com.example.cars.model.Customer;
import cars.com.example.cars.request.AddCustomerRequest;
import cars.com.example.cars.response.MessageResponse;
import cars.com.example.cars.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/addCustomer")
    public ResponseEntity<MessageResponse> addProduct(@RequestBody AddCustomerRequest request) {
        service.saveCustomer(request);
        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/getCustomerById/{customer_id}")
    public Customer getById(@PathVariable Long customer_id) {
        return service.getCustomerById(customer_id);
    }
}
