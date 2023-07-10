package tw.idv.petradisespringboot.admin.vo;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;
import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer id;
    @Column(name = "admin_name")
    private String name;
    @Column(name = "admin_account")
    private String account;
    @Column(name = "admin_password")
    private String password;
    @Column(name = "admin_phone")
    private String phone;
    @Column(name = "admin_address")
    private String address;
    @Column(name = "admin_email")
    private String email;
    @Column(name = "admin_title")
    private AdminTitle title;
    @Column(name = "admin_status", insertable = false)
    private AdminStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "admin", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<AdminAccess> accesses;

}

