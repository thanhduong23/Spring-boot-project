package nhom3.spring.thanh.baitap1.Repository;

import nhom3.spring.thanh.baitap1.Class.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserDemo, Integer>
{
    UserDemo findByEmail(String email);
}