package nhom3.spring.thanh.baitap1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void saveOrUpdate(UserDemo user)
    {
        userRepository.save(user);
    }
    public Iterable<UserDemo> getAllUsers() {
        return userRepository.findAll();
    }
}