package tw.idv.petradisespringboot.animalapplication.service;

import java.util.List;

import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;



public interface AdoptedApplicationService {

    List<AdoptedApplication> getAllAdoptedApplication();

    AdoptedApplication findAdoptedApplicationById(Integer adoptedId);


	AdoptedApplication save(AdoptedApplication adoptedApplication);

	AdoptedApplication addAdoptedApplication(AdoptedApplication adoptedApplication);

	void saveAdoptedApplication(AdoptedApplication request);

	




}