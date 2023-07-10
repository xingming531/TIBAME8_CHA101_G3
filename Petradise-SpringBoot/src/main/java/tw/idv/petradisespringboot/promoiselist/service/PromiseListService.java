package tw.idv.petradisespringboot.promoiselist.service;

import java.util.List;

import tw.idv.petradisespringboot.promoiselist.vo.PromiseList;

public interface PromiseListService {



	PromiseList save(PromiseList promiseList);

	PromiseList findPromiselistById(Integer id);

	List<PromiseList> findAllPromiselist();


	PromiseList addPromiselist(PromiseList promiseList);

	

}
