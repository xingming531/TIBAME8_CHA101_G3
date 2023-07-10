package com.emp.model;
public class HotelOwnerVO  implements java.io.Serializable{
	

	private Integer hotelId;
	private String hotelName;
	private String hotelAddress;
	private String hotelStatus;
	private byte[] hotelLicPic;
	private String ownerAccount;
	private String ownerPassword;
	private String ownerName;
	private String ownerId;
	private String ownerBank;
	private String ownerPhone;
	private String ownerEmail;
	private String ownerAccess;
	private Integer reviewScorePeople;
	private Integer reviewScoreTotal;
	private String hotelLicId;
	
	
//	public HotelOwnerVO(Integer hotelId, String hotelName, String hotelAddress, String hotelStatus, byte[] hotelLicPic,
//			String ownerAccount, String ownerPassword, String ownerName, String ownerId, String ownerBank,
//			String ownerPhone, String ownerEmail, String ownerAccess, Integer reviewScorePeople,
//			Integer reviewScoreTotal, String hotelLicId) {
//		super();
//		this.hotelId = hotelId;
//		this.hotelName = hotelName;
//		this.hotelAddress = hotelAddress;
//		this.hotelStatus = hotelStatus;
//		this.setHotelLicPic(hotelLicPic);
//		this.ownerAccount = ownerAccount;
//		this.ownerPassword = ownerPassword;
//		this.ownerName = ownerName;
//		this.ownerId = ownerId;
//		this.ownerBank = ownerBank;
//		this.ownerPhone = ownerPhone;
//		this.ownerEmail = ownerEmail;
//		this.ownerAccess = ownerAccess;
//		this.reviewScorePeople = reviewScorePeople;
//		this.reviewScoreTotal = reviewScoreTotal;
//		this.hotelLicId = hotelLicId;
//	}



	public Integer getReviewScorePeople() {
		return reviewScorePeople;
	}



	public void setReviewScorePeople(Integer reviewScorePeople) {
		this.reviewScorePeople = reviewScorePeople;
	}



	public Integer getReviewScoreTotal() {
		return reviewScoreTotal;
	}



	public void setReviewScoreTotal(Integer reviewScoreTotal) {
		this.reviewScoreTotal = reviewScoreTotal;
	}



	public String getHotelLicId() {
		return hotelLicId;
	}



	public void setHotelLicId(String hotelLicId) {
		this.hotelLicId = hotelLicId;
	}



	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getHotelStatus() {
		return hotelStatus;
	}
	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
	}

	public String getOwnerAccount() {
		return ownerAccount;
	}
	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	public String getOwnerPassword() {
		return ownerPassword;
	}
	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerBank() {
		return ownerBank;
	}
	public void setOwnerBank(String ownerBank) {
		this.ownerBank = ownerBank;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getOwnerAccess() {
		return ownerAccess;
	}
	public void setOwnerAccess(String ownerAccess) {
		this.ownerAccess = ownerAccess;
	}

	public byte[] getHotelLicPic() {
		return hotelLicPic;
	}

	public void setHotelLicPic(byte[] hotelLicPic) {
		this.hotelLicPic = hotelLicPic;
	}
	
	
}
	
