package nhom3.spring.thanh.baitap1.Class;

import jakarta.persistence.*;

import java.util.List;


@Table(name = "COMPANY")
@Entity
public class Company {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String companyName;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<UserDemo> users;
    @ManyToMany(mappedBy = "companies") // Liên kết ngược với NhanVien
//    @JsonBackReference
//    @JsonManagedReference
    private List<NhanVien> nhanviens;

    public List<UserDemo> getUsers() {
        return users;
    }

    public void setUsers(List<UserDemo> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public List<NhanVien> getNhanviens() {
        return nhanviens;
    }

    public void setNhanviens(List<NhanVien> nhanviens) {
        this.nhanviens = nhanviens;
    }
}