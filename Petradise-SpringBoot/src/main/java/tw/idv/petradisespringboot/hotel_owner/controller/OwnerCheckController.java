package tw.idv.petradisespringboot.hotel_owner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.hotel_owner.service.HotelOwnerService;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// 業主審核
@RestController
@RequestMapping("/ownerCheck")
public class OwnerCheckController {

	@Autowired
	private HotelOwnerService hotelOwnerService;

	@GetMapping("/getAll")
	public List<HotelOwnerVO> getAllOwners() {

		try {
			List<HotelOwnerVO> list = hotelOwnerService.getAll();
			List<HotelOwnerVO> statusList = hotelOwnerService.getStatus();
			for (HotelOwnerVO vo : list) {
				// 只需獲取圖片的數組
				byte[] imageBytes = vo.getHotelLicPic();
				// 只要圖片不為空 就轉為Base64
				if (imageBytes != null) {
					String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

					// VO那邊建一個getter和setter的Base64,好讓下方可放進資料
					vo.setBase64Image(imageBase64);
				}
			}
			return statusList;

		} catch (Exception e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateOwnerStatus(@RequestBody Map<String, String> payload) {
		// 獲取前端回應
		String hotelid = payload.get("hotelId");
		String hotelStatus = payload.get("hotelStatus");

		Integer hotelId = Integer.valueOf(hotelid);
		// 找到要做更新的hotelId

		try {
			hotelOwnerService.updateOwnerStatus(hotelId, hotelStatus);
			return ResponseEntity.ok("OwnerStatus update is successful");
		} catch (Exception e) {
			System.out.println("更新失敗");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
