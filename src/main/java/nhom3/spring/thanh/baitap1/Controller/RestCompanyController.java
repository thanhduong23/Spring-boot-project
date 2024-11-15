package nhom3.spring.thanh.baitap1.Controller;

import nhom3.spring.thanh.baitap1.Class.Company;
import nhom3.spring.thanh.baitap1.Service.CompanyService;
import nhom3.spring.thanh.baitap1.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class RestCompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private NhanVienService nhanVienService;

    // Get all companies (GET /api/company)
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    // Get a company by id (GET /api/company/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Company company = companyService.findById(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    // Create a new company (POST /api/company)
    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.saveOrUpdate(company);
    }

    // Update a company (PUT /api/company/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
        Company existingCompany = companyService.findById(id);
        if (existingCompany != null) {
            updatedCompany.setId(id);
            companyService.saveOrUpdate(updatedCompany);
            return ResponseEntity.ok(updatedCompany);
        }
        return ResponseEntity.notFound().build();
    }
    // Delete a company (DELETE /api/company/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        Company company = companyService.findById(id);
        if (company != null) {
            companyService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
//    @PostMapping("/{companyId}/addNhanVien/{nhanVienId}")
//    public ResponseEntity<Company> addNhanVienToCompany(
//            @PathVariable int companyId,
//            @PathVariable int nhanVienId) {
//
//        Company company = companyService.findById(companyId);
//        NhanVien nhanVien = nhanVienService.getNhanVienById(nhanVienId);
//
//        if (company != null && nhanVien != null) {
//            company.getNhanviens().add(nhanVien);
//            companyService.saveOrUpdate(company);
//            return ResponseEntity.ok(company);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
