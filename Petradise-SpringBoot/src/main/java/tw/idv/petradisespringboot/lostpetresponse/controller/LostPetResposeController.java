package tw.idv.petradisespringboot.lostpetresponse.controller;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.lostpetresponse.service.LostPetResponseService;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@RestController
public class LostPetResposeController {
	

    private final LostPetResponseService responseService;
   

	public LostPetResposeController(LostPetResponseService responseService) {
		this.responseService = responseService;
	}

	@PostMapping("/LostPetRespose/add")
	LostPetResponse create(@RequestBody LostPetResponseDTO lostPetResponseDTO) {


		System.out.println(lostPetResponseDTO);


		return responseService.add(lostPetResponseDTO);
	}
	
	@GetMapping("/LostPetRespose/id={id}")
	LostPetResponse getById(@PathVariable Integer id) {
		return responseService.findById(id);
	}
}
