package nhom3.spring.thanh.baitap1.Repository;

import nhom3.spring.thanh.baitap1.Class.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
