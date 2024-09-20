package nhom3.spring.thanh.baitap1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // Hiển thị trang home với danh sách tất cả user
    @GetMapping("/home")
    public String home(Model model) {
        // Lấy tất cả user từ database
        Iterable<UserDemo> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "home";  // Trả về trang home.html
    }

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
        return "redirect:/home";  // Chuyển hướng về trang home
    }
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
//        return "svResult"; // Trả về file svResult.html để hiển thị kết quả
//    }
//}
