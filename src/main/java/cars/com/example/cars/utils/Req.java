package cars.com.example.cars.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cars.com.example.cars.request.CarRequest;
import cars.com.example.cars.request.CustomerRequest;
import cars.com.example.cars.request.DriverRequest;
import cars.com.example.cars.request.UserRequest;

public class Req {
    @JsonIgnoreProperties("email")
    public static class Login extends UserRequest {}

    public static class Register extends UserRequest {}
       
    @JsonIgnoreProperties("customer_id")
    public static class AddCustomer extends CustomerRequest {}

    public static class EditCustomer extends CustomerRequest {}

    
    @JsonIgnoreProperties("car_id")
    public static class AddCar extends CarRequest {}

    public static class EditCar extends CarRequest {}

    @JsonIgnoreProperties("driver_id")
    public static class AddDriver extends DriverRequest {}

    public static class EditDriver extends DriverRequest {}
}
