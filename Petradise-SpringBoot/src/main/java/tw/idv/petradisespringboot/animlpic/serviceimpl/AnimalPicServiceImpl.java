package tw.idv.petradisespringboot.animlpic.serviceimpl;

import java.util.List;

import javax.persistence.OrderColumn;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animlpic.repo.AnimalPicRepository;
import tw.idv.petradisespringboot.animlpic.service.AnimalPicService;
import tw.idv.petradisespringboot.animlpic.vo.AnimalPic;


@Service
public class AnimalPicServiceImpl implements AnimalPicService {

	private final AnimalPicRepository repository;

	AnimalPicServiceImpl(AnimalPicRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<AnimalPic> getAllAnimalpic() {

		return repository.findAll();
	}

	@Override
	public AnimalPic findAnimalpicById(Integer id) {

		return repository.findById(id).orElseThrow(() -> new AnimalpicNotFoundException(id));
	}
	@Override
	public AnimalPic save (AnimalPic animalpic ) {
		return repository.save(animalpic);
	}
	
	
	@Override
    public AnimalPic addAnimalPic(AnimalPic animalpic) {
		return repository.save(animalpic);
    }
	
	@Override
	public AnimalPic update(AnimalPic animalpic) {
		return repository.save(animalpic);
	}
}

class AnimalpicNotFoundException extends RuntimeException {
	AnimalpicNotFoundException(Integer id) {
		super("找不到會員ID: " + id);
	}

}
