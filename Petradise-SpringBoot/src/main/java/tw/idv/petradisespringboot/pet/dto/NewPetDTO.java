package tw.idv.petradisespringboot.pet.dto;

import lombok.Data;
import tw.idv.petradisespringboot.pet.vo.Pet;

import java.util.List;

@Data
public class NewPetDTO {

    private Pet pet;
    private List<String> pics;
}
