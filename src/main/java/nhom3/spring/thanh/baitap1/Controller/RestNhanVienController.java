package nhom3.spring.thanh.baitap1.Controller;

import nhom3.spring.thanh.baitap1.Class.NhanVien;
import nhom3.spring.thanh.baitap1.Service.CompanyService;
import nhom3.spring.thanh.baitap1.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhanviens")
public class RestNhanVienController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private NhanVienService nhanVienService;

    // 1. GET all NhanVien
    @GetMapping
    public List<NhanVien> getAllNhanViens() {
        return nhanVienService.getAllNhanViens();
    } // 2. GET NhanVien by ID
    @GetMapping("/{id}")
    public NhanVien getNhanVienById(@PathVariable int id) {
        return nhanVienService.getNhanVienById(id);
    }

    // 3. POST - Create new NhanVien
    @PostMapping
    public NhanVien createNhanVien(@RequestBody NhanVien nhanVien) {
        return nhanVienService.saveOrUpdate(nhanVien);
    }

    // 4. PUT - Update NhanVien
    @PutMapping("/{id}")
    public NhanVien updateNhanVien(@PathVariable int id, @RequestBody NhanVien nhanVien) {
        NhanVien existingNhanVien = nhanVienService.getNhanVienById(id);
        if (existingNhanVien != null) {
            existingNhanVien.setName(nhanVien.getName());
            existingNhanVien.setEmail(nhanVien.getEmail());
            existingNhanVien.setCompanies(nhanVien.getCompanies());
            return nhanVienService.saveOrUpdate(existingNhanVien);
        } else {
            throw new RuntimeException("NhanVien not found with ID: " + id);
        }
    }

    // 5. DELETE - Remove NhanVien
    @DeleteMapping("/{id}")
    public String deleteNhanVien(@PathVariable int id) {
        nhanVienService.deleteNhanVien(id);
        return "Deleted NhanVien with ID: " + id;
    }
//    // API thêm công ty vào nhân viên
//    @PostMapping("/{nhanVienId}/addCompany/{companyId}")
//    public ResponseEntity<NhanVien> addCompanyToNhanVien(
//            @PathVariable int nhanVienId,
//            @PathVariable int companyId) {
//
//        NhanVien nhanVien = nhanVienService.getNhanVienById(nhanVienId);
//        Company company = companyService.findById(companyId);
//
//        if (nhanVien != null && company != null) {
//            nhanVien.getCompanies().add(company);
//            nhanVienService.saveOrUpdate(nhanVien);
//            return ResponseEntity.ok(nhanVien);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

