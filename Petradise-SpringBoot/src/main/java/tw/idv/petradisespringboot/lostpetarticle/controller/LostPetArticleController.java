package tw.idv.petradisespringboot.lostpetarticle.controller;

import java.util.List;

import org.hibernate.action.internal.OrphanRemovalAction;
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

import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;


@RestController
@RequestMapping("/LostPetArticle")
public class LostPetArticleController {
	
	private LostPetArticleService lostPetArticleService;
	
	public LostPetArticleController(LostPetArticleService lostPetArticleService) {
		this.lostPetArticleService = lostPetArticleService;
	}
	
	@GetMapping("/all")
	List<LostPetArticle> getArticleWithStatus(){
//		var kk = lostPetArticleService.getAllArticles();
//	
//		System.out.println(kk);
		return lostPetArticleService.getArticlesWithStatus();
	}
	
	@GetMapping("/admin/all")
	List<LostPetArticle> getAllArticleByAdmin(){
		return lostPetArticleService.getAllArticleByAdmin();
	}
	
	@ResponseBody
	@GetMapping("/id={id}")
	LostPetArticle getArticle(@PathVariable Integer id) {
		 
		
	    return lostPetArticleService.findByIdWithStatus(id);
	}
	
	@PostMapping("/create")
	LostPetArticle create(@RequestBody LostPetArticle lostPetArticle) {
		System.out.println(lostPetArticle);
		return lostPetArticleService.add(lostPetArticle);
	}
	
	@PostMapping("/update")
	LostPetArticle update(@RequestBody LostPetArticle lostPetArticle) {
		lostPetArticle.setArticleStatus("0");
		System.out.println(lostPetArticle);
		return lostPetArticleService.update(lostPetArticle);
	}
	
	@PutMapping("/delete/id={id}")
	ResponseEntity<LostPetArticle> editStatus(@PathVariable Integer id) {
	    LostPetArticle article = lostPetArticleService.findById(id);

	    if (article == null) {
	        return ResponseEntity.notFound().build();
	    }

	    lostPetArticleService.update4Status(article);

	    return ResponseEntity.ok(article);
	}

	@GetMapping("/selectBySpecies")
	ResponseEntity<List<LostPetArticle>> findBySpecies(@RequestParam("species") String species) {
	    List<LostPetArticle> articles;
	    
	    switch (species) {
	    case "貓":
	      articles = lostPetArticleService.findBySpecies("貓");
	      break;
	    case "狗":
	      articles = lostPetArticleService.findBySpecies("狗");
	      break;
	    case "鳥":
	      articles = lostPetArticleService.findBySpecies("鳥");
	      break;
	    default:
	      articles = lostPetArticleService.findBySpecies("其他");
	      break;
	  }
	    return ResponseEntity.ok(articles);
	  }
	
	@GetMapping("/memId={memId}")
	ResponseEntity<List<LostPetArticle>> findByMemId(@PathVariable Integer memId){
		
		
		List<LostPetArticle> memArticles = lostPetArticleService.findByMemId(memId);
		return ResponseEntity.ok(memArticles);
	}
}
