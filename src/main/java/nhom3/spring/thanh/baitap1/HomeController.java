package nhom3.spring.thanh.baitap1;

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HomeController {
//    @GetMapping("/trangchu")
//    public String trangChu(Model model) {
//        model.addAttribute("lop", "Thông tin lớp học: Lớp Lập trình Web");
//        return "trangchu"; // Trả về trang HTML "trangchu.html"
//    }
//}

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/trangchu")
    public String trangChu(Model model) {
        model.addAttribute("lop", "Thông tin lớp học: Lớp Lập trình Web");
        return "trangchu"; // Trả về trang HTML "trangchu.html"
    }

    // Phương thức hiển thị form thêm mới sinh viên
    @GetMapping("/addSV")
    public String showForm(Model model) {
        // Tạo object SV và thêm vào model để Thymeleaf sử dụng
        model.addAttribute("sv", new SV());
        return "addSV"; // Trả về file addSV.html
    }

    // Phương thức xử lý dữ liệu khi form được submit
    @PostMapping("/addSV")
    public String submitForm(@ModelAttribute("sv") SV sv, Model model) {
        // Xử lý thông tin sinh viên sau khi form được submit
        model.addAttribute("message", "Thông tin sinh viên: " + sv.getHoTen() + ", CCCD: " + sv.getCccd());
        return "svResult"; // Trả về file svResult.html để hiển thị kết quả
    }
}
