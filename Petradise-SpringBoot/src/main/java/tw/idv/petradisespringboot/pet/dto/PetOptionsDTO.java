package tw.idv.petradisespringboot.pet.dto;

import lombok.Getter;
import lombok.Setter;
import tw.idv.petradisespringboot.pet.vo.enums.PetSize;
import tw.idv.petradisespringboot.pet.vo.enums.PetType;

@Getter
@Setter
public class PetOptionsDTO {
    private PetType[] petType;
    private PetSize[] petSize;

    public PetOptionsDTO(PetType[] petType, PetSize[] petSize) {
        this.petType = petType;
        this.petSize = petSize;
    }

}
