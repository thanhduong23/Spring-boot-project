package nhom3.spring.thanh.baitap1.Repository;

import nhom3.spring.thanh.baitap1.Class.NhanVien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, Integer> {
}
