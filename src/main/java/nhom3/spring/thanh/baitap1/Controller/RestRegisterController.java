package nhom3.spring.thanh.baitap1.Controller;

import nhom3.spring.thanh.baitap1.Class.Role;
import nhom3.spring.thanh.baitap1.Class.UserDemo;
import nhom3.spring.thanh.baitap1.Repository.RoleRepository;
import nhom3.spring.thanh.baitap1.Repository.UserRepository;
import nhom3.spring.thanh.baitap1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    // API đăng ký người dùng mới
    @PostMapping("/register")
    public UserDemo registerUser(@RequestBody UserDemo user) {
        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Gán role mặc định cho người dùng
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            // Nếu chưa tồn tại, lưu vai trò vào database trước
            userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }
        user.getRoles().add(userRole);
        userRepository.save(user);

        // Lưu người dùng xuống database
        return userService.saveOrUpdate(user);
    }


}
