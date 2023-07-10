package tw.idv.petradisespringboot.admin.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_access")
public class AdminAccess {

    @EmbeddedId
    private AdminAccessId id;

    @JsonBackReference
    @ManyToOne
    @MapsId("adminId")
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @MapsId("functionId")
    @JoinColumn(name = "function_id")
    private AccessFunction accessFunction;
}
