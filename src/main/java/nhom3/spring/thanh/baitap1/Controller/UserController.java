package nhom3.spring.thanh.baitap1.Controller;

import nhom3.spring.thanh.baitap1.Class.Company;
import nhom3.spring.thanh.baitap1.Class.NhanVien;
import nhom3.spring.thanh.baitap1.Class.UserDemo;
import nhom3.spring.thanh.baitap1.Service.CompanyService;
import nhom3.spring.thanh.baitap1.Service.NhanVienService;
import nhom3.spring.thanh.baitap1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    UserService userService;
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/list-users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "User/userList"; // Trả về trang userList.html
    }
    // Hiển thị form để thêm mới user
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new UserDemo());  // Thêm object user mới vào model
        return "User/addUser";  // Trả về trang addUser.html
    }

    // Lưu thông tin user xuống database
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserDemo user) {
        userService.saveOrUpdate(user);  // Lưu user xuống database
        return "redirect:/list-users";  // Chuyển hướng về trang home
    }
    @GetMapping("/userDetail/{id}")
    public String userDetail(@PathVariable("id") int id, Model model) {
        UserDemo user = userService.findById(id); // Tìm user theo ID
        model.addAttribute("user", user);
        model.addAttribute("company", user.getCompany()); // Lấy thông tin công ty liên kết với user
        return "User/userDetail"; // Trả về trang userDetail.html
    }

    // Hiển thị form chỉnh sửa người dùng
    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "User/editUser"; // Trả về trang editUser.html
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

    @GetMapping("/addCompany")
    public String add(Model model) {
        model.addAttribute("company", new Company());
        List<UserDemo> users = userService.getAllUsers();
        model.addAttribute("nhanviens", nhanVienService.getAllNhanViens());
        model.addAttribute("users", users);
        return "Company/addCompany";
    }

    @PostMapping("/addCompany")
    public String saveOrUpdate(@ModelAttribute("company") Company company) {
        // Chỉ lưu những người dùng đã chọn từ form
        List<UserDemo> selectedUsers = company.getUsers(); // Nhận danh sách người dùng đã chọn từ đối tượng company
        company.setUsers(selectedUsers); // Gán lại danh sách người dùng cho công ty

        // Lấy danh sách nhân viên từ cơ sở dữ liệu dựa trên các ID đã chọn
        List<NhanVien> selectedNhanviens = company.getNhanviens();
        company.setNhanviens(selectedNhanviens);

        companyService.saveOrUpdate(company); // Lưu công ty vào cơ sở dữ liệu
        return "redirect:/companies";
    }
    @GetMapping("/companies")
    public String trangChiTiet(Model model) {
        List<Company> companies = companyService.getAll();
        System.out.println("companies: " + companies);

        model.addAttribute("companies", companies);
        return "Company/companies";
    }
    @GetMapping("/companyDetail/{id}")
    public String companyDetail(@PathVariable("id") int id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        return "Company/companyDetail";
    }
    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.deleteById(id);
        return "redirect:/companies";
    }

    @GetMapping("/editCompany/{id}")
    public String editCompany(@PathVariable("id") int id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        return "Company/editCompany"; // Dùng chung form thêm/sửa
    }
    @PostMapping("/companies/update")
    public String updateCompany(@ModelAttribute Company company) {
        companyService.saveOrUpdate(company); // Save updated company details
        return "redirect:/companies"; // Redirect to companies list
    }


}
