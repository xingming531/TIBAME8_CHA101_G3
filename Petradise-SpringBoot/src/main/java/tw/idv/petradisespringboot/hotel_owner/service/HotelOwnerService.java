package tw.idv.petradisespringboot.hotel_owner.service;

import java.util.List;

import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerAccess;

//import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

public interface HotelOwnerService {
	// 新增
	void insert(HotelOwnerVO hotelOwnerVO);

	// 刪除
	void delete(Integer hotelId);

	// 修改
	void update(HotelOwnerVO hotelOwnerVO);

	// 查詢單筆資料
	HotelOwnerVO findByPrimaryKey(Integer hotelId);

	// 查詢所有資料
	List<HotelOwnerVO> getAll();

	// 查詢權限
	List<HotelOwnerVO> getStatus();

	List<HotelOwnerVO> getStatus2();

	List<HotelOwnerVO> findByAccess(HotelOwnerAccess ownerAccess);

	void updateOwnerStatus(Integer hotelId, String hotelStatus);

	HotelOwnerVO login(String account, String password);

	List<HotelOwnerVO> findKeyword(String keyword);
}
