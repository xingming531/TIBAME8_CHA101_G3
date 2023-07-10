package tw.idv.petradisespringboot.promoiselist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.idv.petradisespringboot.promoiselist.vo.PromiseList;



public interface PromiseListRepository extends JpaRepository< PromiseList, Integer>{
	
	
}
