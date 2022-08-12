package cars.com.example.cars.model;
import javax.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
  
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;
  
    @Column(nullable = false, unique = true)
    private String token;
  
    @Column(nullable = false)
    private Instant expiryDate;
}
