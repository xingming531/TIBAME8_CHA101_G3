package tw.idv.petradisespringboot.animalapplication.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animalapplication.repo.AdoptedApplicationRepository;
import tw.idv.petradisespringboot.animalapplication.service.AdoptedApplicationService;
import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;
import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.vo.Member;



@Service
public class AdoptedApplicationServiceImpl implements AdoptedApplicationService {

	private final AdoptedApplicationRepository repository;
	
	@Autowired
	  private MemberRepository memberRepository;

	AdoptedApplicationServiceImpl(AdoptedApplicationRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<AdoptedApplication> getAllAdoptedApplication() {
		
		return repository.findAll();
	}

	@Override
	public AdoptedApplication findAdoptedApplicationById(Integer adoptedId) {
	
		return repository
                .findById(adoptedId)
                .orElseThrow(() -> new AdoptedApplicationNotFoundException(adoptedId));
	}


	@Override
	public AdoptedApplication addAdoptedApplication(AdoptedApplication adoptedApplication) {
		
		return repository.save(adoptedApplication);
	}

	
	class AdoptedApplicationNotFoundException extends RuntimeException {
		AdoptedApplicationNotFoundException(Integer id) {
			super("找不到會員ID: " + id);
		}
		
	}
	
	
	@Override
	public void saveAdoptedApplication(AdoptedApplication entity) {
//	    Member member = new Member();
//	    member.setId(request.getAdoptedId());
//	    member.setName(request.getAdopterName());
//	    member.setAddress(request.getAdopterAddress());
//	    member.setPhone(request.getAdopterPhone());
//	    member.setEmail(request.getAdopterEmail());
//
//
//	    memberRepository.save(member);
		
//	    AdoptedApplication application = new AdoptedApplication();
//	    application.setMembId(entity.getMembId());
//	    application.setAnimalId(entity.getAnimalId());
//	    application.setAdopterIdNumber(entity.getAdopterIdNumber());
//	    application.setAdopterName(entity.getAdopterName());
//	    application.setAdopterAddress(entity.getAdopterAddress());
//	    application.setAdopterJob(entity.getAdopterJob());
//	    application.setAdopterEmail(entity.getAdopterEmail());
	  
	    repository.save(entity);
	}


	@Override
	public AdoptedApplication save(AdoptedApplication adoptedApplication) {
		
		return null;
	}

	}

	





