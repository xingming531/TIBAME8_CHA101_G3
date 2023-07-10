package tw.idv.petradisespringboot.lostpetresponse.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetArticleRepository;
import tw.idv.petradisespringboot.lostpetresponse.controller.LostPetResponseDTO;
import tw.idv.petradisespringboot.lostpetresponse.repo.LostPetResponseRepo;
import tw.idv.petradisespringboot.lostpetresponse.service.LostPetResponseService;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

import javax.transaction.Transactional;

@Service
public class LostPetResponseServiceImpl implements LostPetResponseService{

	private LostPetResponseRepo lostPetResponseRepo;

	private LostPetArticleRepository articleRepository;
	
	public LostPetResponseServiceImpl(LostPetResponseRepo lostPetResponseRepo,
			LostPetArticleRepository articleRepository) {
		this.lostPetResponseRepo = lostPetResponseRepo;
		this.articleRepository = articleRepository;
	}
	
	@Transactional
	@Override
	public LostPetResponse add(LostPetResponseDTO responseDTO) {
		// 先從DTO拿到 LostPetResponse
		var response = responseDTO.getResponse();
		// 取得DTO傳過來的 articleId
		var articleId = responseDTO.getArticleId();
		// 透過article repository 取得該id的article
		var article = articleRepository.findById(articleId).orElseThrow(IllegalAccessError::new);
		// 在把Repository找到的article放入要新增的LostPetResponse中
		response.setArticle(article);
		// Call LostPetResponse repo 的 save方法, 存完順便回傳給前端表示成功
		return lostPetResponseRepo.save(response);
	}

	@Override
	public LostPetResponse remove(LostPetResponse lostPetResponse) {
		lostPetResponseRepo.delete(lostPetResponse);
		System.out.println("success delete!");
		return null;
	}

	@Override
	public LostPetResponse edit(LostPetResponse lostPetResponse) {
		return lostPetResponseRepo.save(lostPetResponse);
	}

	@Override
	public LostPetResponse findById(Integer id) {
		return lostPetResponseRepo.findById(id).orElseThrow();
	}


	

}
