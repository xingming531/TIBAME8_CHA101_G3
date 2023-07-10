package tw.idv.petradisespringboot.animalcorporation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animalcorporation.dto.LoginDTO;
import tw.idv.petradisespringboot.animalcorporation.service.AnimalCorporationService;
import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;
import tw.idv.petradisespringboot.email.EmailService;

@RestController
@RequestMapping("/animalCorporation")
public class AnimalCorporationController {
	
	private AnimalCorporationService service;
	private EmailService emailService;
	
	
	
	public AnimalCorporationController(AnimalCorporationService service, EmailService emailService) {
		this.service = service;
		this.emailService = emailService;
	}

	@GetMapping("/all")
	ResponseEntity<List<AnimalCorporation>> allWithStatus2(){
		List<AnimalCorporation> animalCorporations = service.findByStatus('2');
		
		return ResponseEntity.ok(animalCorporations);
	}
	
	@GetMapping("/all/withDefault")
	ResponseEntity<List<AnimalCorporation>> allWithDefault(){
		List<AnimalCorporation> animalCorporations = service.findByStatus('0');
		
		return ResponseEntity.ok(animalCorporations);
	}
	
	@PostMapping("/update")
	AnimalCorporation update(@RequestBody AnimalCorporation editAnimalCorporation) {
		return service.update(editAnimalCorporation);
	}
	
	@PostMapping("/apply")
	ResponseEntity<?> apply(@RequestBody AnimalCorporation animalCorporation) {
		
		if (animalCorporation == null) {
			ResponseEntity.notFound().build();
		}
		if (ResponseEntity.ok() != null) {
			emailService.sendEmail(animalCorporation.getContactEmail(), 
					"您的Petradise帳戶正在審核中", 
					"Hi," + animalCorporation.getContactName() + "\n"
					+ "已收到您的帳戶申請" + "\n"
					+ "請靜待回覆" + "\n"
					+ "感謝您的申請\n"
					+ "Petradise團隊敬上");
		}
		
		service.add(animalCorporation);
	
		return ResponseEntity.ok("OK");
	}
	
	@ResponseBody
	@GetMapping("/id={id}")
	AnimalCorporation  findionById(@PathVariable Integer id) {
	    return service.findByID(id);
	}
	
	@PutMapping("/editCorpAccess/id={id}")
	ResponseEntity<?> editCorpAccess(@PathVariable Integer id){
		AnimalCorporation corporation = service.findByID(id);
		
		if (corporation == null) {
			return ResponseEntity.notFound().build();
		}
		service.updateByCorpAccess(corporation);
		return ResponseEntity.ok(corporation);
	}

	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody LoginDTO dto) {
		try {
			var vo = service.login(dto.getAccount(), dto.getPassword());
			return ResponseEntity.ok(vo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/status/corpId={corpId}")
	ResponseEntity<?> editAppliedStatus(@RequestParam("appliedStatus") Character appliedStatus, @PathVariable Integer corpId ){
		
		AnimalCorporation animalCorporation = service.findByID(corpId);
		
		if (animalCorporation == null) {
			return ResponseEntity.notFound().build();
		}
		if (appliedStatus == '2') {
			emailService.sendEmail(animalCorporation.getContactEmail(), 
					"您的Petradise帳戶已就緒 - 立即開始使用", 
					"Hi," + animalCorporation.getContactName() + "\n"
							+ "您已成功通過帳號申請成為合作業主，\n"
							+ "您的帳號是" +  animalCorporation.getCorpAccount() + "\n"
							+ "您的密碼是" + animalCorporation.getCorpPassword() + "\n"
							+ "請至 http://34.81.60.7:8080/animalCorporation/signin.html 登入服務\n"
							+ "Petradise團隊敬上");
		} else if (appliedStatus == '1'){
			emailService.sendEmail(animalCorporation.getContactEmail(), 
					"您的Petradise帳戶申請結果", 
					"Hi," + animalCorporation.getContactName() + "\n"
					+ "您的申請結果為未通過\n"
					+ "感謝您的申請\n"
					+ "Petradise期待繼續替您服務\n"
					+ "Petradise團隊敬上");
		}
		animalCorporation.setAppliedStatus(appliedStatus);
		service.update(animalCorporation);
		
		return ResponseEntity.ok("success !");
	}
	
	
}
