package cars.com.example.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cars.com.example.cars.model.Brand;
import cars.com.example.cars.response.MessageResponse;
import cars.com.example.cars.service.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService service;

    @PostMapping("/addBrand")
    public ResponseEntity<MessageResponse> addProduct(@RequestBody Brand brand) {
        service.saveBrand(brand);
        return ResponseEntity.ok(new MessageResponse("Success"));
    }
}
