package cars.com.example.cars.response;
import lombok.Data;
import java.time.Instant;

@Data
public class TokenRefreshResponse {
  private String accessToken;
  private String refreshToken;
  private Instant refreshTokenExpDate;
  private String tokenType = "Bearer";

  public TokenRefreshResponse(String accessToken, String refreshToken,Instant refreshTokenExpDate) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.refreshTokenExpDate = refreshTokenExpDate;
  }

  public Instant getRefreshTokenExpDate() {
    return this.refreshTokenExpDate;
  }

  public void setRefreshTokenExpDate(Instant refreshTokenExpDate) {
    this.refreshTokenExpDate = refreshTokenExpDate;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String token) {
    this.accessToken = token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

}