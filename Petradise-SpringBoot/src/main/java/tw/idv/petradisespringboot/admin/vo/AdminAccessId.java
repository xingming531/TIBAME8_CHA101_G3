package tw.idv.petradisespringboot.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AdminAccessId implements Serializable {
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "function_id")
    private Integer functionId;

}
