package tw.idv.petradisespringboot.lostpetresponse.service;

import tw.idv.petradisespringboot.lostpetresponse.controller.LostPetResponseDTO;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

public interface LostPetResponseService {
	
	LostPetResponse add(LostPetResponseDTO lostPetResponseDTO);
	
	LostPetResponse remove(LostPetResponse lostPetResponse);
	
	LostPetResponse edit(LostPetResponse lostPetResponse);
	
	LostPetResponse findById(Integer id);
	
	
	
}
