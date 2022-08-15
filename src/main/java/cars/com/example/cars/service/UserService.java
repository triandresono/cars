package cars.com.example.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cars.com.example.cars.model.Users;
import cars.com.example.cars.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public Boolean existsByEmail(String username) {
        return repository.existsByEmail(username);
    }

    public List<Users> inquiryAdmin(String param) {
        return repository.inquiryAdmin(param);
    }

    public void deleteAdminById(Long id) {
        repository.deleteById(id);
    }

    public void saveOrUpdateAdmin(Users user){
        repository.save(user);
    }
}
