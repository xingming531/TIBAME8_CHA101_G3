package tw.idv.petradisespringboot.animalapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animalapplication.service.AdoptedApplicationService;
import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;
import tw.idv.petradisespringboot.member.vo.Member;




@RequestMapping("/adopted_applications")
@RestController
public class AdoptedApplicationController {
	
	private final AdoptedApplicationService service;

	public AdoptedApplicationController(AdoptedApplicationService service) {
		this.service = service;
	}
	

	@GetMapping("/all")
	List<AdoptedApplication> all() {
		
		return service.getAllAdoptedApplication();
	}

	@PostMapping("/save")
	AdoptedApplication newAdoptedApplication(@RequestBody AdoptedApplication adoptedApplication) {
		return service.addAdoptedApplication(adoptedApplication);
	}

	@ResponseBody
	@GetMapping("{id}") // /adopted_applications/17
	AdoptedApplication findAdoptedApplicationById(@PathVariable Integer id) {
		return service.findAdoptedApplicationById(id);
	}
	
	
	@PostMapping("/update/{id}")
	public AdoptedApplication updateAdoptedApplication(@PathVariable Integer id, @RequestBody AdoptedApplication updatedApplication) {
	   
	    AdoptedApplication existingApplication = service.findAdoptedApplicationById(id);
	    if (existingApplication != null) {
	        
	        existingApplication.setMembId(updatedApplication.getMembId());
	        existingApplication.setAnimalId(updatedApplication.getAnimalId());
	        existingApplication.setAdopterIdNumber(updatedApplication.getAdopterIdNumber());
	        existingApplication.setAdopterName(updatedApplication.getAdopterName());
	        existingApplication.setAdopterAddress(updatedApplication.getAdopterAddress());
	        existingApplication.setAdopterPhone(updatedApplication.getAdopterPhone());
	        existingApplication.setAdopterJob(updatedApplication.getAdopterJob());
	        existingApplication.setAdopterEmail(updatedApplication.getAdopterEmail());
	        existingApplication.setAdopterStatus(updatedApplication.getAdopterStatus());
	        
	       
	        return service.addAdoptedApplication(existingApplication);
	    } else {
	        
	        return null;
	    }
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createAdoptedApplication(@RequestBody AdoptedApplication request) {
	
//	    Member loggedInMember = getCurrentLoggedInMember(); // 请替换成您自己获取当前登录会员的方法
//
//	  
//	    AdoptedApplication application = new AdoptedApplication();
//	    application.setMembId(loggedInMember.getId());
//	    application.setAnimalId(request.getAnimalId());
//	    application.setAdopterIdNumber(request.getAdopterIdNumber());
//	    application.setAdopterIdNumber(loggedInMember(""));
//	    application.setAdopterName(loggedInMember.getName());
//	    application.setAdopterAddress(loggedInMember.getAddress());
//	    application.setAdopterJob(loggedInMember(""));
//	    application.setAdopterEmail(loggedInMember.getEmail());

	
	    service.addAdoptedApplication(request);

	    return ResponseEntity.ok("Adopted application created successfully.");
	}


	private Member getCurrentLoggedInMember() {
		
		return null;
	}


	private String loggedInMember(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	
	
	
	

	



		
	
}







