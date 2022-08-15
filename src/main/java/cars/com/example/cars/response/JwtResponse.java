package cars.com.example.cars.response;
import java.time.Instant;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private Instant refreshTokenExpDate;

	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email,Instant refreshTokenExpDate) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.refreshTokenExpDate = refreshTokenExpDate;
	}


	public Instant getRefreshTokenExpDate() {
		return this.refreshTokenExpDate;
	}

	public void setRefreshTokenExpDate(Instant refreshTokenExpDate) {
		this.refreshTokenExpDate = refreshTokenExpDate;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
