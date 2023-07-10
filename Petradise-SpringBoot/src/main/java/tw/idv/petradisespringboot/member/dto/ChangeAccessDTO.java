package tw.idv.petradisespringboot.member.dto;

import lombok.Data;
import tw.idv.petradisespringboot.member.vo.MemberAccess;

@Data
public class ChangeAccessDTO {
    private Integer id;
    private MemberAccess access;
}
