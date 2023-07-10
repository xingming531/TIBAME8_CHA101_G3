package tw.idv.petradisespringboot.lostpetresponse.controller;

import lombok.Data;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@Data
public class LostPetResponseDTO {

    private Integer articleId;
    private LostPetResponse response;
    
    public LostPetResponseDTO () {
    	response = new LostPetResponse();
    }
}