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
import cars.com.example.cars.model.Driver;
import cars.com.example.cars.repository.DriverRepository;
import cars.com.example.cars.request.SingleStringRequest;
import cars.com.example.cars.response.CommonResponse;
import cars.com.example.cars.service.DriverService;
import cars.com.example.cars.utils.Constant;
import cars.com.example.cars.utils.Req;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    DriverService service;

    @Autowired
    DriverRepository repository;

    @PostMapping("/addDriver")
    @Operation(summary = "addDriver", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> addDriver(@RequestBody Req.AddDriver request) {
        try {
            repository.save(Constant.toDriver(request));
            return CommonResponse.success("Driver Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/updateDriver")
    @Operation(summary = "updateDriver", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> updateDriver(@RequestBody Req.EditDriver req) {
        try {
            repository.save(Constant.toDriver(req));
            return CommonResponse.success("Driver Updated");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteDriverById/{driver_id}")
    @Operation(summary = "deleteDriverById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> deleteDriverById(@PathVariable Long driver_id) {
        try {
            repository.deleteById(driver_id);
            return CommonResponse.success("Driver Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getDriverById/{driver_id}")
    @Operation(summary = "getDriverById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> getDriverById(@PathVariable Long driver_id) {
        try {
            Driver cust = repository.findById(driver_id).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, cust);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/inquiryDriver")
    @Operation(summary = "inquiryDriver", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> inquiryDriver(@RequestBody SingleStringRequest request) {
        try {
            List<Driver> custs = service.inquiryDriver(request.getValue());
            return CommonResponse.common("OK", HttpStatus.OK, custs);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
