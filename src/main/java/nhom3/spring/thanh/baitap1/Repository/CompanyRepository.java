package nhom3.spring.thanh.baitap1.Repository;

import nhom3.spring.thanh.baitap1.Class.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
}
