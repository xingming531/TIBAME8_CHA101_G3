package tw.idv.petradisespringboot.admin.vo;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "access_function")
public class AccessFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id")
    private Integer id;
    @Column(name = "function_name")
    private Character name;

}