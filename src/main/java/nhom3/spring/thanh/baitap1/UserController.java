package nhom3.spring.thanh.baitap1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // Hiển thị trang home với danh sách tất cả user
//    @GetMapping("/home")
//    public String home(Model model) {
//        // Lấy tất cả user từ database
//        Iterable<UserDemo> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "home";  // Trả về trang home.html
//    }

    // Hiển thị form để thêm mới user
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new UserDemo());  // Thêm object user mới vào model
        return "addUser";  // Trả về trang addUser.html
    }

    // Lưu thông tin user xuống database
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserDemo user) {
        userService.saveOrUpdate(user);  // Lưu user xuống database
        return "redirect:/list-users";  // Chuyển hướng về trang home
    }
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "userDetail"; // Trả về trang userDetail.html
        } else {
            return "userNotFound"; // Trả về trang lỗi nếu không tìm thấy user
        }
    }
    @GetMapping("/list-users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList"; // Trả về trang userList.html
    }

    // Hiển thị form chỉnh sửa người dùng
    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "editUser"; // Trả về trang editUser.html
        } else {
            return "redirect:/list-users"; // Nếu không tìm thấy user, redirect về danh sách
        }
    }

    // Xử lý lưu thông tin user sau khi chỉnh sửa
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") UserDemo user) {
        userService.saveOrUpdate(user);
        return "redirect:/list-users"; // Sau khi update, chuyển về danh sách người dùng
    }
    // Xóa user theo ID
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/list-users"; // Redirect về trang danh sách user sau khi xóa
    }
//    @GetMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable("id") int id) {
//        userService.deleteUser(id);
//        return "redirect:/home"; // Chuyển hướng về trang chính sau khi xóa
//    }
}

//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class UserController {
//    @GetMapping("/trangchu")
//    public String trangChu(Model model) {
//        model.addAttribute("lop", "Thông tin lớp học: Lớp Lập trình Web");
//        return "home"; // Trả về trang HTML "home.html"
//    }
//
//    // Phương thức hiển thị form thêm mới sinh viên
//    @GetMapping("/addSV")
//    public String showForm(Model model) {
//        // Tạo object SV và thêm vào model để Thymeleaf sử dụng
//        model.addAttribute("sv", new UserDemo());
//        return "addUser"; // Trả về file addUser.html
//    }
//
//    // Phương thức xử lý dữ liệu khi form được submit
//    @PostMapping("/addSV")
//    public String submitForm(@ModelAttribute("sv") UserDemo sv, Model model) {
//        // Xử lý thông tin sinh viên sau khi form được submit
//        model.addAttribute("message", "Thông tin sinh viên: " + sv.getHoTen() + ", CCCD: " + sv.getCccd());
//        return "svResult"; // Trả về file userNotFound.html để hiển thị kết quả
//    }
//}
