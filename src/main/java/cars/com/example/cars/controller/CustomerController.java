package cars.com.example.cars.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cars.com.example.cars.model.Customer;
import cars.com.example.cars.repository.CustomerRepository;
import cars.com.example.cars.request.SingleStringRequest;
import cars.com.example.cars.response.CommonResponse;
import cars.com.example.cars.service.CustomerService;
import cars.com.example.cars.utils.Constant;
import cars.com.example.cars.utils.Req;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository repository;

    @PostMapping("/addCustomer")
    @Operation(summary = "addCustomer", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> addCustomer(@RequestBody Req.AddCustomer request) {
        try {
            repository.save(Constant.toCustomer(request));
            return CommonResponse.success("Customer Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/updateCustomer")
    @Operation(summary = "updateCustomer", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> updateCustomer(@RequestBody Req.EditCustomer req) {
        try {
            repository.save(Constant.toCustomer(req));
            return CommonResponse.success("Customer Updated");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @DeleteMapping("/deleteCustomerById/{customer_id}")
    @Operation(summary = "deleteCustomerById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long customer_id) {
        try {
            repository.deleteById(customer_id);
            return CommonResponse.success("Customer Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getCustomerById/{customer_id}")
    @Operation(summary = "getCustomerById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> getCustomerById(@PathVariable Long customer_id) {
        try {
            Customer cust = repository.findById(customer_id).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, cust);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/inquiryCustomer")
    @Operation(summary = "inquiryCustomer", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> inquiryCustomer(@RequestBody SingleStringRequest request) {
        try {
            List<Customer> lists = service.inquiryCustomer(request.getValue());
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
