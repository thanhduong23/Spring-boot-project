package nhom3.spring.thanh.baitap1.Controller;

import nhom3.spring.thanh.baitap1.Class.UserDemo;
import nhom3.spring.thanh.baitap1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<UserDemo> users() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDemo> getUserById(@PathVariable int id) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    // Create a new user (POST /api/users)
    @PostMapping("/api/users")
    public UserDemo createUser(@RequestBody UserDemo user) {
        return userService.saveOrUpdate(user);
    }
    // Update a user (PUT /api/users/{id})
    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserDemo> updateUser(@PathVariable int id, @RequestBody UserDemo updatedUser) {
        UserDemo existingUser = userService.findById(id);
        if (existingUser != null) {
            // Preserve the existing role and password
            updatedUser.setRoles(existingUser.getRoles());  // Preserve the role

            // Set the ID to ensure the correct user is updated
            updatedUser.setId(id);
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());  // Hash the new password
                updatedUser.setPassword(hashedPassword);
            } else {
                updatedUser.setPassword(existingUser.getPassword());  // Keep the existing password if no new password is provided
            }
            // Save the updated user
            userService.saveOrUpdate(updatedUser);

            // Return the updated user
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a user (DELETE /api/users/{id})
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


