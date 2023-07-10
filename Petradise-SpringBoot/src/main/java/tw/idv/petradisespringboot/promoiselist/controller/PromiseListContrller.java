package tw.idv.petradisespringboot.promoiselist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.promoiselist.service.PromiseListService;
import tw.idv.petradisespringboot.promoiselist.vo.PromiseList;

@RequestMapping("/promise_lists")
@RestController
public class PromiseListContrller {

	@Autowired
	private  PromiseListService service;

	

	@GetMapping("/all")
	List<PromiseList> all() {
		return service.findAllPromiselist();
	}

	@PostMapping("/save")
	PromiseList savePromise_list(@RequestBody PromiseList promise_list) {
		return service.addPromiselist(promise_list);
	}

	@GetMapping("id/{id}")
	PromiseList one(@PathVariable Integer id) {
		return service.findPromiselistById(id);
	}
	
	
}

class promise_listNotFoundException extends RuntimeException {

	promise_listNotFoundException(Integer id) {
		super("找不到寵物ID: " + id);
	}

}
