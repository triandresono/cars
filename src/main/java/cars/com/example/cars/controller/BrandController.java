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
import cars.com.example.cars.model.Brand;
import cars.com.example.cars.repository.BrandRepository;
import cars.com.example.cars.request.SingleStringRequest;
import cars.com.example.cars.response.CommonResponse;
import cars.com.example.cars.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService service;

    @Autowired
    BrandRepository repository;

    @PostMapping("/addBrand/{brand_name}")
    @Operation(summary = "addBrand", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> addBrand(@PathVariable String brand_name) {
        try {
            if (service.isExist(brand_name)) {
                return CommonResponse.fail("Error: Brand Name is already taken!");
            }
            service.saveBrand(brand_name);
            return CommonResponse.success("Brand Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/updateBrand")
    @Operation(summary = "updateBrand", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> updateBrand(@RequestBody Brand brand) {
        try {
            repository.save(brand);
            return CommonResponse.success("Brand Updated");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteBrandById/{brand_id}")
    @Operation(summary = "deleteBrandById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> deleteBrandById(@PathVariable Long brand_id) {
        try {
            repository.deleteById(brand_id);
            return CommonResponse.success("Brand Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }



    @PostMapping("/inquiryBrand")
    @Operation(summary = "inquiryBrand", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> inquiryBrand(@RequestBody SingleStringRequest request) {
      try {
        List<Brand> brands = service.inquiryBrand(request.getValue());
        return CommonResponse.common("OK", HttpStatus.OK, brands);
      } catch (Exception e) {
        return CommonResponse.fail(e.getMessage());
      }
    }

    @GetMapping("/findBrandById/{brand_id}")
    @Operation(summary = "findBrandById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> findBrandById(@PathVariable Long brand_id) {
      try {
        Brand brands = repository.findById(brand_id).orElse(null);
        return CommonResponse.common("OK", HttpStatus.OK, brands);
      } catch (Exception e) {
        return CommonResponse.fail(e.getMessage());
      }
    }
}
