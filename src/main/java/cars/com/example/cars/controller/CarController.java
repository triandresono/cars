package cars.com.example.cars.controller;
import cars.com.example.cars.model.Car;
import cars.com.example.cars.repository.CarRepository;
import cars.com.example.cars.request.SingleStringRequest;
import cars.com.example.cars.response.CommonResponse;
import cars.com.example.cars.service.CarService;
import cars.com.example.cars.utils.Constant;
import cars.com.example.cars.utils.Req;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired  
    CarService service;

    @Autowired
    CarRepository repository;

    @PostMapping("/addCar")
    @Operation(summary = "addCar", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> addCar(@RequestBody Req.AddCar request) {
        try {
            repository.save(Constant.toCar(request));
            return CommonResponse.success("Add Car Success");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/updateCar")
    @Operation(summary = "updateCar", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> updateCar(@RequestBody Req.EditCar request) {
        try {
            repository.save(Constant.toCar(request));
            return CommonResponse.success("Car Updated");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteCarById/{car_id}")
    @Operation(summary = "deleteCarById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> deleteCarById(@PathVariable Long car_id) {
        try {
            repository.deleteById(car_id);
            return CommonResponse.success("Car Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getCarById/{car_id}")
    @Operation(summary = "getCarById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> getCarById(@PathVariable Long car_id) {
        try {
            Car car = repository.findById(car_id).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, car);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/inquiryCar")
    @Operation(summary = "inquiryCar", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> inquiryCar(@RequestBody SingleStringRequest request) {
        try {
            List<Car> car = service.inquiryCar(request.getValue());
            return CommonResponse.common("OK", HttpStatus.OK, car);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
