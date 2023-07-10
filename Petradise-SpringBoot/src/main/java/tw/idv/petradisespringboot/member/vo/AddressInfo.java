package tw.idv.petradisespringboot.member.vo;

import lombok.Data;

import java.util.List;

@Data
public class AddressInfo {

    private String name;
    private List<District> districts;

}
