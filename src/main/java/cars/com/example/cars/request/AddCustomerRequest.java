package cars.com.example.cars.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cars.com.example.cars.model.Customer;
import cars.com.example.cars.model.SystemMaster;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCustomerRequest {
    @NotBlank
    @Size(max = 30)
    private String customer_name;

    @NotBlank
    @Size(max = 30)
    private String customer_gender;

    @NotBlank
    @Size(max = 30)
    private String customer_address;

    @NotBlank
    @Size(max = 30)
    private String phone_number;

    @NotBlank
    @Size(max = 30)
    private String customer_guarantee;

    @NotBlank
    @Size(max = 30)
    private String id_number;

    public Customer toCustomer(AddCustomerRequest req) {
        Customer model = new Customer();
        SystemMaster gender = new SystemMaster();
        SystemMaster hold = new SystemMaster();

        gender.setSystem_code(req.getCustomer_gender());
        hold.setSystem_code(req.getCustomer_guarantee());
        
        model.setCustomer_name(req.getCustomer_name());
        model.setCustomer_address(req.getCustomer_address());
        model.setPhone_number(req.getPhone_number());
        model.setId_number(req.getId_number());
        
        model.setCustomer_gender(gender);
        model.setCustomer_guarantee(hold);
        return model;
    }
}
