package cars.com.example.cars.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "transaction_table")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer_id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car_id;

    @NotBlank
    private Instant checkin_time;

    @NotBlank
    private Instant checkout_time;

    @ManyToOne
    @JoinColumn(name = "payment_type", referencedColumnName = "system_code")
    private SystemMaster payment_type;

    @ManyToOne
    @JoinColumn(name = "car_return_status", referencedColumnName = "system_code")
    private SystemMaster car_return_status;

    @ManyToOne
    @JoinColumn(name = "transaction_driver_type", referencedColumnName = "system_code")
    private SystemMaster transaction_driver_type;

    // OPTIONAL
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    private Driver driver_id;

    @Size(max = 50)
    private String driver_price_day;

    @Size(max = 50)
    private String driver_price_hour;
}
