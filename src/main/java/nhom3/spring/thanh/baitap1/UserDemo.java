package nhom3.spring.thanh.baitap1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class UserDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Tự động tăng ID
    @Column
    private int id;

    @Column
    private String firstName;

    @Column
    private String email;

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


//
//public class UserDemo {
//    private String cccd;
//    private String hoTen;
//    private String ngaySinh;
//    private String gioiTinh;
//    private String diaChi;
//
//    // Constructor không tham số
//    public UserDemo() {
//    }
//
//    // Constructor với các tham số
//    public UserDemo(String cccd, String hoTen, String ngaySinh, String gioiTinh, String diaChi) {
//        this.cccd = cccd;
//        this.hoTen = hoTen;
//        this.ngaySinh = ngaySinh;
//        this.gioiTinh = gioiTinh;
//        this.diaChi = diaChi;
//    }
//
//    // Getter và Setter cho cccd
//    public String getCccd() {
//        return cccd;
//    }
//
//    public void setCccd(String cccd) {
//        this.cccd = cccd;
//    }
//
//    // Getter và Setter cho hoTen
//    public String getHoTen() {
//        return hoTen;
//    }
//
//    public void setHoTen(String hoTen) {
//        this.hoTen = hoTen;
//    }
//
//    // Getter và Setter cho ngaySinh
//    public String getNgaySinh() {
//        return ngaySinh;
//    }
//
//    public void setNgaySinh(String ngaySinh) {
//        this.ngaySinh = ngaySinh;
//    }
//
//    // Getter và Setter cho gioiTinh
//    public String getGioiTinh() {
//        return gioiTinh;
//    }
//
//    public void setGioiTinh(String gioiTinh) {
//        this.gioiTinh = gioiTinh;
//    }
//
//    // Getter và Setter cho diaChi
//    public String getDiaChi() {
//        return diaChi;
//    }
//
//    public void setDiaChi(String diaChi) {
//        this.diaChi = diaChi;
//    }
//}
