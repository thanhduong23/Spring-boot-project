package nhom3.spring.thanh.baitap1.Controller;

import nhom3.spring.thanh.baitap1.Repository.RoleRepository;
import org.springframework.ui.Model;
import nhom3.spring.thanh.baitap1.Class.Role;
import nhom3.spring.thanh.baitap1.Class.UserDemo;
import nhom3.spring.thanh.baitap1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        return "login"; // This will render the login.html
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDemo());
        return "register";
    }

    @PostMapping("/process-register")
    public String processRegister(UserDemo user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
//        Set<Role> roles = new HashSet<>(Arrays.asList(new Role("USER")));
//        user.setRoles(roles);
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            userRole = new Role("USER"); // Create new role if it doesn't exist
            roleRepository.save(userRole); // Persist the role
        }

        user.getRoles().add(userRole);
        userRepository.save(user);

        return "redirect:/login";
    }


}