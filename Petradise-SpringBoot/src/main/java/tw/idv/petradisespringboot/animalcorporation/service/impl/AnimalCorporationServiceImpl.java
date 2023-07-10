package tw.idv.petradisespringboot.animalcorporation.service.impl;

import java.util.List;

import javax.persistence.Id;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.animalcorporation.exceptions.AccountNotActiveException;
import tw.idv.petradisespringboot.animalcorporation.exceptions.AccountNotFoundException;
import tw.idv.petradisespringboot.animalcorporation.repo.AnimalCorporationRepository;
import tw.idv.petradisespringboot.animalcorporation.service.AnimalCorporationService;
import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;
import tw.idv.petradisespringboot.email.EmailService;

import java.util.List;
import java.util.Objects;

@Service
public class AnimalCorporationServiceImpl implements AnimalCorporationService{
	
	private AnimalCorporationRepository animalCorporationRepository;
	private EmailService emailService;
	

	public AnimalCorporationServiceImpl(AnimalCorporationRepository animalCorporationRepository,
			EmailService emailService) {
		this.animalCorporationRepository = animalCorporationRepository;
		this.emailService = emailService;
	}

	

	@Override
	public AnimalCorporation add(AnimalCorporation animalCorporation) {
		return animalCorporationRepository.save(animalCorporation);
	}

	@Override
	public AnimalCorporation update(AnimalCorporation editAnimalCorporation) {
		return animalCorporationRepository.save(editAnimalCorporation);
	}

	@Override
	public List<AnimalCorporation> findAllWithStatusNot1() {
		
		List<AnimalCorporation> animalCorporations = animalCorporationRepository.findByAppliedStatusNot('1');
		
		return animalCorporations;
	}

	@Override
	public AnimalCorporation findByID(Integer id) {
		return animalCorporationRepository.findById(id).orElseThrow();
	}

	@Override
	public AnimalCorporation updateByCorpAccess(AnimalCorporation animalCorporation) {

		Character currentCorpAccess = animalCorporation.getCorpAccess();

	    if (currentCorpAccess.equals('1')) {
	        animalCorporation.setCorpAccess('0');
	    } else if (currentCorpAccess.equals('0')) {
	        animalCorporation.setCorpAccess('1');
	    }
		return animalCorporationRepository.save(animalCorporation);
	}

	@Override
	public AnimalCorporation login(String account, String password) {
		var optionalVO = animalCorporationRepository.findByCorpAccountAndCorpPassword(account, password);
		if (optionalVO.isEmpty()) {
			throw new AccountNotFoundException("帳號或密碼錯誤");
		}
		var vo = optionalVO.get();
		if (Objects.equals(vo.getCorpAccess(), '1')) {
			throw  new AccountNotActiveException("該帳號已被停權");
		}
		return vo;

	}
	public List<AnimalCorporation> findByStatus(Character appliedStatus) {
		List<AnimalCorporation> animalCorporations = animalCorporationRepository.findByAppliedStatus(appliedStatus);
		
		return animalCorporations;
	}

	

}
