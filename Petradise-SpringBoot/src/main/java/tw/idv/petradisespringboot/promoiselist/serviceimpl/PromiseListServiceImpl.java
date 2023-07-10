package tw.idv.petradisespringboot.promoiselist.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animal.vo.Animal;
import tw.idv.petradisespringboot.promoiselist.repo.PromiseListRepository;
import tw.idv.petradisespringboot.promoiselist.service.PromiseListService;
import tw.idv.petradisespringboot.promoiselist.vo.PromiseList;

@Service
public class PromiseListServiceImpl implements PromiseListService {

	private final PromiseListRepository repository;

	PromiseListServiceImpl(PromiseListRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<PromiseList> findAllPromiselist() {

		return repository.findAll();
	}

	@Override
	public PromiseList findPromiselistById(Integer id) {

		return repository.findById(id).orElseThrow(() -> new Promise_listNotFoundException(id));
	}

	@Override
	public PromiseList save(PromiseList promiseList) {
		return repository.save(promiseList);
	}



	@Override
	public PromiseList  addPromiselist(PromiseList  promiseList) {

		return repository.save(promiseList);
	}
}

	class Promise_listNotFoundException extends RuntimeException {
		Promise_listNotFoundException(Integer id) {
	        super("找不到會員ID: " + id);
	    }
   
	}

