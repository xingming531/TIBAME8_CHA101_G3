package tw.idv.petradisespringboot.animlpic.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animlpic.service.AnimalPicService;
import tw.idv.petradisespringboot.animlpic.vo.AnimalPic;
import tw.idv.petradisespringboot.animlpic.vo.AnimalPicRequestVO;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@RestController
@RequestMapping("/animal_pics")
public class AnimalPicController {

	private  AnimalPicService service;
	
	public AnimalPicController(AnimalPicService service) {
	
		this.service = service;
	}

	@GetMapping("/all")
	List<AnimalPic> getAllAnimalpic() {
		var test = service.getAllAnimalpic();
		System.out.println(test);
		return service.getAllAnimalpic();
	}

	@PostMapping("/save")
	AnimalPic newAnimal_pic(@RequestBody AnimalPic animal_pic) {
		return service.save(animal_pic);
	}

	@GetMapping("/{id}")
	AnimalPic one(@PathVariable Integer id) {
		return service.findAnimalpicById(id);

	}
	
	
	
	@PostMapping("/update")
	AnimalPic update(@RequestBody AnimalPic animal_pic) {
		System.out.println(animal_pic);
		return service.update(animal_pic);
		
	}
	
	
	
	
}

class Animal_picNotFoundException extends RuntimeException {

	Animal_picNotFoundException(Integer id) {
		super("找不到寵物ID: " + id);
	}

}
