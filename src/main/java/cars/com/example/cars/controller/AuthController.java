package cars.com.example.cars.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cars.com.example.cars.exception.TokenRefreshException;
import cars.com.example.cars.model.RefreshToken;
import cars.com.example.cars.model.Users;
import cars.com.example.cars.repository.UserRepository;
// import cars.com.example.cars.request.SignupRequest;
import cars.com.example.cars.request.TokenRefreshRequest;
import cars.com.example.cars.response.CommonResponse;
import cars.com.example.cars.response.JwtResponse;
import cars.com.example.cars.response.MessageResponse;
import cars.com.example.cars.response.TokenRefreshResponse;
import cars.com.example.cars.security.jwt.JwtUtils;
import cars.com.example.cars.security.services.RefreshTokenService;
import cars.com.example.cars.security.services.UserDetailsImpl;
import cars.com.example.cars.utils.Req;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody Req.Login loginRequest) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String jwt = jwtUtils.generateJwtToken(userDetails);
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
    JwtResponse resp = new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
        userDetails.getUsername(), userDetails.getEmail(), refreshToken.getExpiryDate());
    return CommonResponse.common("Login Success", HttpStatus.OK, resp);
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(requestRefreshToken);
    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(((Users) user).getUsername());
          TokenRefreshResponse resp = new TokenRefreshResponse();
          resp.setAccessToken(token);
          resp.setRefreshToken(requestRefreshToken);
          resp.setRefreshTokenExpDate(refreshToken.get().getExpiryDate());
          return ResponseEntity.ok(resp);
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }

  @PostMapping("/logout/{user_id}")
  public ResponseEntity<?> logoutUser(@PathVariable(name = "user_id") Long user_id) {
    refreshTokenService.deleteByUserId(user_id);
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

}
