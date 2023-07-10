package tw.idv.petradisespringboot.hotel_owner.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.email.EmailService;
import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/ownerSignUP")
public class OwnerSignUpController {

	private final HotelOwnerServiceImpl hotelOwnerServiceImpl;
	private final EmailService emailService;

	@Autowired
	public OwnerSignUpController(HotelOwnerServiceImpl hotelOwnerServiceImpl, EmailService emailService) {
		this.hotelOwnerServiceImpl = hotelOwnerServiceImpl;
		this.emailService = emailService;
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insertOwner(HttpServletRequest req, @RequestBody HotelOwnerVO hotelOwnerVO) {
		HttpSession session = req.getSession();

		try {
			byte[] imgData = Base64.getDecoder().decode(hotelOwnerVO.getBase64Image());
			hotelOwnerVO.setHotelLicPic(imgData);
			session.setAttribute("hotel_owner", hotelOwnerVO);
			System.out.println("Image data: " + Arrays.toString(imgData));
			hotelOwnerServiceImpl.insert(hotelOwnerVO);
			String email = (String) session.getAttribute("email");
			String subject = "Petradise註冊通知";
			String text = "親愛的業主您好,這裡已收到您的註冊申請表單,目前正在審核中,請耐心等候審核通知～";
			emailService.sendEmail(email, subject, text);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("新增成功");

	}

	@PostMapping("/checkMail")
	public void checkMail(HttpServletRequest req, @RequestBody Map<String, String> payload) {
		HttpSession session = req.getSession();
		String token = UUID.randomUUID().toString().substring(0, 6);
		String subject = "Petradise 信箱驗證碼";
		String text = "您好,您的驗證碼為: " + token;
		String owenrEmail = payload.get("ownerEmail");
		emailService.sendEmail(owenrEmail, subject, text);
		session.setAttribute("email_verification_token", token);
		session.setAttribute("email", owenrEmail);
	}

	@PostMapping("/verify-email")
	public String verifyEmail(HttpServletRequest req, @RequestParam("token") String token) {
		HttpSession session = req.getSession();

		String storedToken = (String) session.getAttribute("email_verification_token");
//		String email = (String) session.getAttribute("email");

		if (token.equals(storedToken)) {
//			String subject = "Petradise信箱驗證通知";
//			String text = "親愛的業主您好,此信箱已完成驗證,請接續完成表單";
//			emailService.sendEmail(email, subject, text);

			return "信箱驗證成功";
		}

		return "信箱驗證失敗";
	}

}
