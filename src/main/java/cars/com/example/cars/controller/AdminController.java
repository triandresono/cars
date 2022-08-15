package cars.com.example.cars.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import cars.com.example.cars.model.Users;
import cars.com.example.cars.repository.UserRepository;
import cars.com.example.cars.request.SingleStringRequest;
import cars.com.example.cars.response.CommonResponse;
import cars.com.example.cars.response.GetAdminResponse;
import cars.com.example.cars.service.UserService;
import cars.com.example.cars.utils.Constant;
import cars.com.example.cars.utils.Req;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  @Autowired
  UserService service;

  @Autowired
  UserRepository repository;

  @PostMapping("/addAdmin")
  @Operation(summary = "addAdmin", security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<?> registerAdmin(@Valid @RequestBody Req.Register request) {
    try {
      if (service.existsByUsername(request.getUsername())) {
        return CommonResponse.fail("Error: Username is already taken!");
      }
      if (service.existsByEmail(request.getEmail())) {
        return CommonResponse.fail("Error: Email is already in use!");
      }

      service.saveOrUpdateAdmin(Constant.user(request));
      return CommonResponse.success("Admin Registered");
    } catch (Exception e) {
      return CommonResponse.fail(e.getMessage());
    }
  }

  @PostMapping("/inquiryAdmin")
  @Operation(summary = "inquiryAdmin", security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<?> inquiryAdmin(@RequestBody SingleStringRequest request) {
    try {
      List<Users> admin = service.inquiryAdmin(request.getValue());
      return CommonResponse.common("OK", HttpStatus.OK, GetAdminResponse.toListResponse(admin));
    } catch (Exception e) {
      return CommonResponse.fail(e.getMessage());
    }
  }


  @GetMapping("/findAdminById/{user_id}")
  @Operation(summary = "findAdminById", security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<Object> findAdminById(@PathVariable Long user_id) {
    try {
      Users user = repository.findById(user_id).orElse(null);
      return CommonResponse.common("OK", HttpStatus.OK, user);
    } catch (Exception e) {
      return CommonResponse.fail(e.getMessage());
    }
  }

  @DeleteMapping("/deleteAdminById/{user_id}")
  @Operation(summary = "deleteAdminById", security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<Object> deleteAdminById(@PathVariable Long user_id) {
      try {
          repository.deleteById(user_id);
          return CommonResponse.success("Admin Deleted");
      } catch (Exception e) {
          return CommonResponse.fail(e.getMessage());
      }
  }
}
