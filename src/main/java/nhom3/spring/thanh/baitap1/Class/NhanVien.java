package nhom3.spring.thanh.baitap1.Class;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "NHANVIEN")
@Entity
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @ManyToMany
    @JoinTable(
            name = "nhanvien_company", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "nhanvien_id"), // Khóa ngoại từ NhanVien
            inverseJoinColumns = @JoinColumn(name = "company_id") // Khóa ngoại từ Company
    )
//    @JsonManagedReference
//    @JsonBackReference

    private List<Company> companies; // Liên kết nhiều công ty

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}